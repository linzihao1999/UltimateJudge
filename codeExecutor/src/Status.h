#ifndef EXECUTOR_STATUS_H
#define EXECUTOR_STATUS_H

enum class AcceptorStatus {
    UNKNOWN,
    ERROR,
    ACCEPT_CONNECTION
};

enum class ReactorStatus {
    OK,
    ERROR
};

#endif //EXECUTOR_STATUS_H
