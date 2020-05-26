# Reproduce Steps

1. Start a zookeeper on 127.0.0.1:2181.
2. Run com.github.seraphain.demo.dubbo.Server. Before the server exits, run com.github.seraphain.demo.dubbo.Client.
3. Use jps to get client pid: `jps | grep Client`.
4. Use jstack to get dubbo threads number: `jstack -l [pid] | grep Dubbo | wc -l`. The result is 50 or 51.
5. After the server exits, run com.github.seraphain.demo.dubbo.Server again.
6. Use jstack to get dubbo thread number: `jstack -l [pid] | grep Dubbo | wc -l`. The result is 100 or 101.
7. Repeat step 5 & 6, dubbo thread number keep increasing everytime the server start.

