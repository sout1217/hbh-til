package com.example.thread_local.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class ThreadLocalTests {

    private final NonThreadLocalService nonThreadLocalService = new NonThreadLocalService();    // @Service (싱글톤 이라 가정했을 때)
    private final ThreadLocalService threadLocalService = new ThreadLocalService();             // @Service (싱글톤 이라 가정했을 때)

    @Test
    void test1() {
        log.info("main start");

        Runnable storeOne = () -> {
            nonThreadLocalService.storageNumber(1); // nonThreadLocalService 인스턴스의 number 값을 1로 바꾼다
        };
        Runnable storeTwo = () -> {
            nonThreadLocalService.storageNumber(2); // nonThreadLocalService 인스턴스의 number 값을 2로 바꾼다
        };

        Thread thread1 = new Thread(storeOne);
        thread1.setName("쓰레드 1");
        Thread thread2 = new Thread(storeTwo);
        thread2.setName("쓰레드 2");

        thread1.start();
        sleep(100); // 동시성 문제 발생
        thread2.start();

        sleep(3000);
        /**
         * 메인 쓰레드가 끝날 떄까지 대기하지 않을 경우 test가 조기에 종료되어 로그가 마지막까지 안 찍힐 수 있으므로 마지막에 sleep(3000);을 추가했습니다.
         * */

        log.info("main exit");
    }

    @Test
    void test2() {
        log.info("main start");

        Runnable storeOne = () -> {
            threadLocalService.storageNumber(1);
        };
        Runnable storeTwo = () -> {
            threadLocalService.storageNumber(2);
        };

        Thread thread1 = new Thread(storeOne);
        thread1.setName("쓰레드 1");
        Thread thread2 = new Thread(storeTwo);
        thread2.setName("쓰레드 2");

        thread1.start();
        sleep(100); // 동시성 문제 해결
        thread2.start();

        sleep(3000);

        log.info("main exit");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
