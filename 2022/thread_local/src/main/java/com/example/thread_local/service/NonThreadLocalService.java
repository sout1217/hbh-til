package com.example.thread_local.service;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class NonThreadLocalService {

    private Integer numberStorage;

    public Integer storageNumber(Integer number) {

        log.info("저장할 번호: {}, 기존에 저장된 번호: {}", number, numberStorage);
        numberStorage = number;

        sleep(1000);

        log.info("저장된 번호 조회: {}", numberStorage);

        return numberStorage;
    }

    private static void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
