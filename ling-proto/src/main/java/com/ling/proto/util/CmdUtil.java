package com.ling.proto.util;

import com.google.protobuf.ByteString;
import com.ling.proto.Command;
import jdk.internal.joptsimple.internal.Strings;

/**
 * @author zhangling
 * @date 2021/12/13 7:28 下午
 */
public class CmdUtil {
    private CmdUtil(){

    }

    private static Command.ResultMessage resultMessage(int resultCode, String msg) {
        Command.ResultMessage.Builder builder = Command.ResultMessage.newBuilder();
        builder.setCode(resultCode);
        if (!Strings.isNullOrEmpty(msg)) {
            builder.setMsg(msg);
        }
        return builder.build();
    }

    public static Command.Package pkg(int resultCode, String msg, int cmdType, ByteString content) {
        Command.Package.Builder builder = Command.Package.newBuilder();
        builder.setResultMsg(resultMessage(resultCode, msg));
        builder.setCmdType(cmdType);
        if (content != null) {
            builder.setContent(content);
        }
        return builder.build();
    }

    public static Command.PackageGroup packageGroup(Command.Package... packages) {
        Command.PackageGroup.Builder builder = Command.PackageGroup.newBuilder();
        for (Command.Package pkg : packages) {
            builder.addPackages(pkg);
        }
        return builder.build();
    }
}
