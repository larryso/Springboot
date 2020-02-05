package com.larry.service;

import java.util.List;

import com.larry.entity.MyScore;
import com.larry.entity.UserAddress;
import com.larry.entity.UserPO;
import com.larry.entity.shop.ShopOrder;

public interface AuthUserService {
	public UserPO save(UserPO user);
	public UserPO getUser(String weixinOpendID);
	public UserPO getUserById(int userId);
	public UserAddress save(UserAddress userAddress);
	public List<UserAddress> getAddressByUser(UserPO user);
	public UserAddress getAddressById (int id);
	public void deleteAddressByID(int id);
	public void updateScore(MyScore score);
	public int getPayableScore(Integer userId);
	public void saveShopOrder(ShopOrder order);
	public List<MyScore> getScoresByUserID(Integer userID);
	public List<ShopOrder>getAllShopOrdersByUserId(Integer userId);
	public ShopOrder getShopOrderById(Integer orderId);

}
