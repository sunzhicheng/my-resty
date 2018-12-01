package cn.web.resource.user.service;

import cn.dreampie.orm.transaction.Transaction;
import cn.web.resource.user.model.User;

/**
 * Created by wangrenhui on 15/1/2.
 */
public interface UserService {
  @Transaction
  public User save(User u);

  @Transaction
  public User update(User u);
}
