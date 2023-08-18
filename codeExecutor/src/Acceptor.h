#ifndef EXECUTOR_ACCEPTOR_H
#define EXECUTOR_ACCEPTOR_H

#include "Connection.h"

class Acceptor {
public:
    static Connection waitAndAcceptConnection(int);
};


#endif //EXECUTOR_ACCEPTOR_H
