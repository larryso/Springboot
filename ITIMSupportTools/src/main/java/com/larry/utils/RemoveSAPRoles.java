package com.larry.utils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPModification;
import com.novell.ldap.LDAPSearchResults;

public class RemoveSAPRoles {
	public static void main(String[] args) {
		/**
		 * defined connection parameters
		 */
		String ldapHost = "US70UUAPP007.zam.alcatel-lucent.com";
		String loginDN = "cn=root";
		String password = "password";
		String searchBase = "ou=person,ou=0,ou=0000000000000000000,DC=ALCATEL-LUCENT,DC=COM";//I cannot remember the person branch, please check
		String searchFilter = "csl=larryso"; //construct your search filter, it could be constructed dynamically
		int ldapPort = LDAPConnection.DEFAULT_PORT;
		// Search Scope
		// SCOPE_BASE、SCOPE_ONE、SCOPE_SUB、SCOPE_SUBORDINATESUBTREE
		int searchScope = LDAPConnection.SCOPE_SUB;
      
		LDAPConnection lc = new LDAPConnection();
		
		List<LDAPModification> modList = new ArrayList<LDAPModification>();//define the modified attribute
		LDAPAttribute erroles = null; 
		try {
			lc.connect(ldapHost, ldapPort);
			lc.bind(LDAPConnection.LDAP_V3, loginDN, password.getBytes("UTF8"));
			LDAPSearchResults searchResults = lc.search(searchBase,
					searchScope, searchFilter, null, false);
			//iterate seach result, in our case if should only one record
			while(searchResults.hasMore()) {
				LDAPEntry entry = searchResults.next(); // get the entry from the search result
				System.out.println("DN: "+entry.getDN());
				String dn = entry.getDN();// get the entry DN and will be used for entry update
				LDAPAttributeSet attributeSet = entry.getAttributeSet();//get all attributes of the entry
				Iterator iter = attributeSet.iterator();
				// iterate entry attributes
				while(iter.hasNext()) {
					LDAPAttribute attribute = (LDAPAttribute) iter.next();
					String attributeName = attribute.getName();
					// get erroles attribute, because we need modify this value
					if("erroles".equalsIgnoreCase(attributeName)) {
						String[] newerRoles;
						List<String> valueList = new ArrayList<String> ();
						Enumeration allValues = attribute.getStringValues();//erroles is a multiple value attributes, so it returns enum type
						if(allValues != null) {
							while (allValues.hasMoreElements()) {
								String value = (String) allValues.nextElement();
								//exclude the role in SOX Audit list
								if(!value.equals("sox exclude role")) {
									valueList.add(value);
								}
							}

						}
						newerRoles = (String[]) valueList.toArray();
						erroles = new LDAPAttribute("erroles", newerRoles);//construct the new erroles attributes and assign new value
					}
				}
				//Iterate entry attributes end
				if(erroles != null) {
					modList.add(new LDAPModification(LDAPModification.REPLACE, erroles));
				}
				LDAPModification[] mods = new LDAPModification[modList.size()];
				mods = (LDAPModification[]) modList.toArray(mods);
				lc.modify(dn, mods);//modify the entry in LDAP
			}
			//iterate entry end
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(lc.isConnected()) {
				try {
					lc.disconnect();
				} catch (LDAPException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
