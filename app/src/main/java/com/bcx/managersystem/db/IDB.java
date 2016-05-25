package com.bcx.managersystem.db;


import com.bcx.managersystem.entity.MyEntity;

import java.util.List;

public interface IDB {
	public List<MyEntity> getAllMessage();
	public void UpdataMessage(MyEntity entity);
	public void saveMessage(MyEntity entity);
	public void deleteMessage(MyEntity entity);
}
