package cn.web;

import cn.dreampie.server.provider.jetty.JettyServerProvider;

/**
 * Created by Dreampie on 16/9/6.
 */
public class Run {

  public static void main(String[] args) throws Exception {
	  JettyServerProvider jettyServer = new JettyServerProvider();
	  jettyServer.build().start();
  }
}
