package cn.dreampie;

import cn.dreampie.server.provider.jetty.JettyServerProvider;

/**
 * Created by Dreampie on 16/9/6.
 */
public class Run {

  public static void main(String[] args) throws Exception {
    new JettyServerProvider().build().start();
  }
}
