package ru.geekbrains.config;

class ConfigFromFixedValues implements ServerConfig {

    @Override
    public String getWww() {
        return "D:\\GeekBrains\\!GIT\\ARCH_08.22\\geek-architecture-02\\www";
    }

    @Override
    public int getPort() {
        return 8088;
    }
}
