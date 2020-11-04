package org.thingsboard.gateway.extensions.modbus.conf;

public class ModbusExtensionConstants {
    public static final int DEFAULT_MODBUS_TCP_PORT = 502;
    public static final int DEFAULT_SOCKET_TIMEOUT = 3000; // in milliseconds

    public static final int DEFAULT_POLL_PERIOD = 1000; // in milliseconds
    public static final int NO_POLL_PERIOD_DEFINED = 0;

    public static final int DEFAULT_REGISTER_COUNT = 1;

    public static final int DEFAULT_BIT_INDEX_FOR_BOOLEAN = 0;
    public static final int NO_BIT_INDEX_DEFINED = -1;
    public static final int DEFAULT_REGISTER_COUNT_FOR_BOOLEAN = 1;

    public static final int MIN_BIT_INDEX_IN_REG = 0;
    public static final int MAX_BIT_INDEX_IN_REG = 15;

    public static final String LITTLE_ENDIAN_BYTE_ORDER = "LITTLE";
    public static final String BIG_ENDIAN_BYTE_ORDER = "BIG";
    public static final String BIG_ENDIAN_BYTE_SWAP = "BIG_SWAP"; //2 byte and 2 byte exchange

    public static final int WORD_REGISTER_COUNT = 1;
    public static final int INTEGER_REGISTER_COUNT = 2;
    public static final int LONG_REGISTER_COUNT = 4;

    public static final int FLOAT_REGISTER_COUNT = 2;
    public static final int DOUBLE_REGISTER_COUNT = 4;
}
