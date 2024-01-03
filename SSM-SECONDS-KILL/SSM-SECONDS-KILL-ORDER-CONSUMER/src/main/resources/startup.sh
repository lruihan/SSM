#!/usr/bin/env bash

kill -9 `cat /home/crossoverJie/order-consumer/application.pid`

nohup java -jar order-consumer-0.0.1-SNAPSHOT.jar -Xmx128m -Xms128m --logging.config=/data/schedule/kafka/bigdata/config/logback.xml --logging.file=/dev/null  > /dev/null 2>&1 &