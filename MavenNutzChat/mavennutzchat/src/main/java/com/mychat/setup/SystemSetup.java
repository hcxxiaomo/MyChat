package com.mychat.setup;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import com.mychat.entity.Flock;
import com.mychat.entity.FlockRefUser;
import com.mychat.entity.Friends;
import com.mychat.entity.Group;
import com.mychat.entity.Message;
import com.mychat.entity.User;
import com.mychat.msg.entity.ChatMessage;
/**
 * Describe:服务器环绕处理
 * Author:陆小不离
 * Age:Eighteen
 * Time:2017年5月5日 下午2:01:34
 */
public class SystemSetup implements Setup{
	
	/**
	 * 在服务器销毁时将所有用户置为hide,防止非正常退出状态未改变的情况
	 */
	@Override
	public void destroy(NutConfig nc) {
		Ioc ioc = nc.getIoc(); // 拿到Ioc容器
	    Dao dao = ioc.get(Dao.class); // 通过Ioc容器可以拿到你想要的ioc bean
		int i = dao.update("user",Chain.make("status", "hide"),Cnd.where("1","=",1));
		System.out.println(i>0?"所有用户已下线...":"下线失败...");
	}

	@Override
	public void init(NutConfig conf) {
		 Ioc ioc = conf.getIoc();
	        Dao dao = ioc.get(Dao.class);
	        // 如果没有createTablesInPackage,请检查nutz版本
//	        Daos.createTablesInPackage(dao, "com.xiaomo.main", false);
	        dao.create(User.class, false);
	        // 初始化默认根用户
//	        if (dao.count(User.class) == 0) {
//	            User user = new User();
//	        }
	        dao.create(Flock.class, false);
	        dao.create(ChatMessage.class, false);
	        dao.create(Group.class, false);
	        dao.create(FlockRefUser.class, false);
	        dao.create(Friends.class, false);
	        dao.create(Message.class, false);
	}
}
