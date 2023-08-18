cmake_minimum_required(VERSION 3.00)
project(executor)

set(CMAKE_CXX_STANDARD 17)

add_executable(executor
        src/main.cpp
        src/Reactor.cpp
        src/Reactor.h
        src/Acceptor.cpp
        src/Acceptor.h
        src/Handler.cpp
        src/Handler.h
        src/Connection.h
        src/ReactorStatus.h
        src/AcceptorStatus.h
)
