package com.example.thread_local.service;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ThreadLocalService {

    private ThreadLocal<Integer> numberStorage = new ThreadLocal<>();

    public Integer storageNumber(Integer number) {

        log.info("저장할 번호: {}, 기존에 저장된 번호: {}", number, numberStorage.get());
        numberStorage.set(number);

        sleep(1000);

        log.info("저장된 번호 조회: {}", numberStorage.get());

        return numberStorage.get();
    }

    private static void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
