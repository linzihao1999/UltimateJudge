#ifndef EXECUTOR_CONNECTION_H
#define EXECUTOR_CONNECTION_H

#include "Acceptor.h"
#include "Status.h"

class Connection {
public:
    explicit Connection(int fd = 0, AcceptorStatus status = AcceptorStatus::UNKNOWN) :
            fd(fd), status(status) {}

    int fd;
    AcceptorStatus status;
};

#endif //EXECUTOR_CONNECTION_H
