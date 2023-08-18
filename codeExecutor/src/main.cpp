#include <iostream>
#include <cstdlib>
#include <unistd.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <cstring>

int main(int argc, char **argv) {
    int listenPort = 9125;

    if (getenv("PORT") != NULL) {
        listenPort = atoi(getenv("CODE_EXECUTOR_PORT"));
        printf("Get listen port from ENV: %d\n", listenPort);
    }

    sockaddr_in serverAddr{};
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_addr.s_addr = INADDR_ANY;
    serverAddr.sin_port = htons(listenPort);
    memset(serverAddr.sin_zero, 0, sizeof(serverAddr.sin_zero));

    int listenSocket = socket(AF_INET, SOCK_STREAM, 0);
    if (listenSocket == -1) {
        printf("Error creating socket\n");
        return 1;
    }

    if (bind(listenSocket, (struct sockaddr *) &serverAddr, sizeof(serverAddr)) == -1) {
        perror("Error binding");
        close(listenSocket);
        return 1;
    }

    if (listen(listenSocket, 10) == -1) {
        perror("Error listening");
        close(listenSocket);
        return 1;
    }
    printf("Listening on port: %d\n", listenPort);

    sockaddr_in clientAddr{};
    socklen_t clientAddrLen = sizeof(clientAddr);
    int clientSocket = accept(listenSocket, (struct sockaddr *) &clientAddr, &clientAddrLen);
    if (clientSocket == -1) {
        printf("Error accepting connection\n");
        close(listenSocket);
        return 1;
    }
    printf("Accepted connection from %s:%u\n", inet_ntoa(clientAddr.sin_addr), ntohs(clientAddr.sin_port));

    char buffer[4096];
    long bytesRead;
    while (true) {
        bytesRead = recv(clientSocket, buffer, sizeof(buffer), 0);
        std::cout << bytesRead << std::endl;
        if (bytesRead <= 0) {
            printf("Error receiving data\n");
            break;
        } else {
            buffer[bytesRead] = '\0';
            std::cout << "Received: " << buffer << std::endl;
            if (strncmp(buffer, "ping", 4) == 0) {
                std::cout << "send pong" << std::endl;
                send(clientSocket, "pong", 4, 0);
            }

        }
    }
    close(clientSocket);
    close(listenSocket);
    return 0;
}
