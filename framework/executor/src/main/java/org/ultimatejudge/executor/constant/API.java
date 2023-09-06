package org.ultimatejudge.executor.constant;

public class API {
    private static class Layout {
        public static final String ROOT = "";
        public static final String DOCKER = ROOT + "/docker";
        public static final String DATA = ROOT + "/data";
        public static final String CODE = ROOT + "/code";
    }

    public static final String PING = Layout.ROOT + "/ping";

    public static final String DATA_UPLOAD = Layout.DATA + "/upload";

    public static final String CODE_JUDGE = Layout.CODE + "/judge";

    public static final String CODE_UPLOAD = Layout.CODE + "/upload";

    public static final String DOCKER_INFO = Layout.DOCKER + "/info";
}
