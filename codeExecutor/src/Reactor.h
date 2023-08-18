#ifndef EXECUTOR_REACTOR_H
#define EXECUTOR_REACTOR_H

#include "Status.h"
#include "Acceptor.h"

const int MAX_CONNECTIONS = 10;
const int TIMEOUT = 1000;//ms
class Reactor {
public:
    explicit Reactor();

    void run();

    void setListenAddr(sockaddr_in *);

    ReactorStatus initSocket();

    ~Reactor();

private:
    sockaddr_in *listenAddr;
    int listenFD;
    int epollFD;
    Acceptor acceptor;
};

#endif //EXECUTOR_REACTOR_H
