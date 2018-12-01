package cn.web.resource.user;

import java.util.Date;
import java.util.List;
import java.util.Set;

import cn.base.resource.api.ApiResource;
import cn.dreampie.orm.aspect.AspectFactory;
import cn.dreampie.orm.page.FullPage;
import cn.dreampie.orm.transaction.TransactionAspect;
import cn.dreampie.route.annotation.API;
import cn.dreampie.route.annotation.DELETE;
import cn.dreampie.route.annotation.GET;
import cn.dreampie.route.annotation.POST;
import cn.dreampie.route.annotation.PUT;
import cn.web.resource.user.model.User;
import cn.web.resource.user.service.UserService;
import cn.web.resource.user.service.UserServiceImpl;

/**
 * Created by ice on 14-12-29.
 */
@API("/users")
public class UserResource extends ApiResource {
  //用于使用service层的 事务
  // @Transaction(name = {"default", "demo"})的注解需要写在service的接口上
  // 注意java的自动代理必须存在接口
  private UserService userService = AspectFactory.newInstance(new UserServiceImpl(), new TransactionAspect());

  //查询集合
  @GET
  public List<User> findAll() {
    return User.dao.findAll();
  }

  //查询单个user对象
  @GET("/:id")
  public User find(String id) {
    return User.dao.findById(id);
  }

  //全部对象分页 http://localhost:8081/api/v1.0/users/1/10
  @GET("/:pageNumber/:pageSize")
  public FullPage<User> paginate(int pageNumber, int pageSize) {
    return User.dao.fullPaginateAll(pageNumber, pageSize);
  }

  //按条件分页 http://localhost:8081/api/v1.0/users/1/10/x
  @GET("/:page_Number/:_page$Size/:$term")
  public FullPage<User> paginate(int page_Number, int _page$Size, String $term) {
    return User.dao.fullPaginateBy(page_Number, _page$Size, "username=?", $term);
  }

  //更新
  @PUT
  public User put(User user) {
    user.update();
    return user;
  }

  //保存
  @POST("/:id")
  public Set<User> save(int id, Set<User> users) {
    userService.save(users.iterator().next());
    return users;
  }

  //删除
  @DELETE("/:id")
  public boolean put(String id) {
    String xx = getParam("xx");
    return User.dao.deleteById(id);
  }


  @GET("/transactions")
//  @Transaction
  public User transaction() {
    User u = new User().set("username", "testtr").set("providername", "test").set("password", "123456").set("sid", "1").set("created_at", new Date());
//    UserInfo userInfo = null;
//    if (u.get("user_info") == null) {
//      userInfo = new UserInfo().set("gender", 0);
//    } else {
//      userInfo = u.get("user_info");
//    }
//    if (u.save()) {
//      userInfo.set("user_id", u.get("id"));
//      userInfo.save();

//      int[] a = new int[0];
//      System.out.println(a[2]);  报错 让事务回滚
//    }
//    u.set("id", u.get("id")).set("username", "x").update();
//    return u;
    //service层的事务
    userService.save(u);
    return null;
  }

}
