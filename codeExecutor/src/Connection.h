#ifndef EXECUTOR_CONNECTION_H
#define EXECUTOR_CONNECTION_H

#include "Acceptor.h"
#include "AcceptorStatus.h"

class Connection {
public:
    explicit Connection(int connectionSocket = 0, AcceptorStatus status = AcceptorStatus::UNKNOWN) :
            connectionSocket(connectionSocket), status(status) {}

    int connectionSocket;
    AcceptorStatus status;
};

#endif //EXECUTOR_CONNECTION_H
