#include <cstdlib>
#include <cstdio>
#include <netinet/in.h>
#include <cstring>
#include "Reactor.h"

int main(int argc, char **argv) {
    int listenPort = 9125;

    if (getenv("PORT") != NULL) {
        listenPort = atoi(getenv("CODE_EXECUTOR_PORT"));
        printf("Get listen port from ENV: %d\n", listenPort);
    }

    auto *serverAddr = new sockaddr_in;
    serverAddr->sin_family = AF_INET;
    serverAddr->sin_addr.s_addr = INADDR_ANY;
    serverAddr->sin_port = htons(listenPort);
    bzero(&serverAddr->sin_zero, sizeof(serverAddr->sin_zero));

    Reactor reactor;
    reactor.setListenAddr(serverAddr);
    reactor.run();
    return 0;
}
