#ifndef EXECUTOR_REACTOR_H
#define EXECUTOR_REACTOR_H

#include "ReactorStatus.h"

const int MAX_CONNECTIONS = 10;

class Reactor {
public:
    explicit Reactor();

    void run();

    void setListenAddr(sockaddr_in *);

    ReactorStatus initSocket();

    ~Reactor();

private:
    sockaddr_in *listenAddr;
    int listenSocket;
};

#endif //EXECUTOR_REACTOR_H
