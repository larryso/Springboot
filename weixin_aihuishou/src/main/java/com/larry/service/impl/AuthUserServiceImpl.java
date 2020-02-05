package com.larry.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larry.dao.UserAddressDAO;
import com.larry.dao.UserDAO;
import com.larry.dao.UserScoreDAO;
import com.larry.dao.shop.ShopOrderDAO;
import com.larry.entity.MyScore;
import com.larry.entity.UserAddress;
import com.larry.entity.UserPO;
import com.larry.entity.shop.ShopOrder;
import com.larry.service.AuthUserService;

@Service
public class AuthUserServiceImpl implements AuthUserService {
	@Autowired
	private UserDAO userDao;
	@Autowired
	private UserAddressDAO userAddressDao;
	@Autowired
	private UserScoreDAO userScoreDao;
	@Autowired
	private ShopOrderDAO shopOrderDao;

	public UserPO save(UserPO user) {
		// TODO Auto-generated method stub
		return userDao.save(user);
	}

	public UserPO getUser(String weixinOpendID) {
		System.out.println(weixinOpendID);
		// TODO Auto-generated method stub
		List<UserPO> userList = (List<UserPO>) userDao.findAuthUserByWeixinOpenID(weixinOpendID);
		if (userList == null || userList.size() <= 0) {
			return null;
		} else {
			userList.sort(new Comparator<UserPO>() {

				public int compare(UserPO o1, UserPO o2) {
					// TODO Auto-generated method stub
					return o1.getId() - o2.getId();
				}

			});
			return userList.get(0);
		}

	}

	public UserAddress save(UserAddress userAddress) {
		// TODO Auto-generated method stub
		return userAddressDao.save(userAddress);
	}

	public List<UserAddress> getAddressByUser(UserPO user) {

		// TODO Auto-generated method stub
		return userAddressDao.findAllAddressByUser(user.getId());
	}

	public UserAddress getAddressById(int id) {
		// TODO Auto-generated method stub
		return userAddressDao.findAddressById(id);
	}

	public void deleteAddressByID(int id) {
		userAddressDao.deleteById(id);

	}

	public void updateScore(MyScore score) {
		userScoreDao.save(score);

	}

	public int getPayableScore(Integer userID) {
		List<MyScore> allMyScore = userScoreDao.getAllScoreByUserId(userID);
		int allInScore = 0;
		int allOutScore = 0;
		for (MyScore s : allMyScore) {
			if (s.getScore_type() == 0) {
				allInScore += s.getScore();
			} else if (s.getScore_type() == 1) {
				allOutScore += s.getScore();
			}
		}

		return allInScore - allOutScore;
	}

	public void saveShopOrder(ShopOrder order) {
		// TODO Auto-generated method stub
		shopOrderDao.save(order);
	}

	public List<MyScore> getScoresByUserID(Integer userID) {
		List<MyScore> allMyScore = userScoreDao.getAllScoreByUserId(userID);
		return allMyScore;
	}

	public List<ShopOrder> getAllShopOrdersByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return shopOrderDao.getAllOrdersByUserId(userId);
	}

	public ShopOrder getShopOrderById(Integer orderId) {

		// TODO Auto-generated method stub
		return shopOrderDao.getOne(orderId);
	}

	public UserPO getUserById(int userId) {
		// TODO Auto-generated method stub
		return userDao.getOne(userId);
	}

}
