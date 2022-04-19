package com.stla.figure.utility;

import java.util.UUID;

public class UUIDGenerator {

    // 生成UUID
    public static String get(){
        return UUID.randomUUID().toString().replaceAll("-", "") ;
    }

}
