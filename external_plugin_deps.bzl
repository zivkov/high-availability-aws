load("//tools/bzl:maven_jar.bzl", "maven_jar")

def external_plugin_deps():
    maven_jar(
        name = "aws_sdk_sns",
        artifact = "software.amazon.awssdk:sns:2.29.15",
        sha1 = "3271de1c86221da6ab2859dc21a7a5aad088456e",
    )

    maven_jar(
        name = "aws_sdk_sqs",
        artifact = "software.amazon.awssdk:sqs:2.29.15",
        sha1 = "d169f0e0645a4281d34758828b85762b52d643b8",
    )

    maven_jar(
        name = "aws_sdk_netty",
        artifact = "software.amazon.awssdk:netty-nio-client:2.29.15",
        sha1 = "2f17728a99424a63c1cfc8e37e3c4ecd48281872",
    )

    maven_jar(
        name = "aws_auth",
        artifact = "software.amazon.awssdk:auth:2.29.15",
        sha1 = "a158e23aa752f7a240ba08def255bcc81465fec3",
    )

    maven_jar(
        name = "aws_regions",
        artifact = "software.amazon.awssdk:regions:2.29.15",
        sha1 = "2a07489790581760abdadaffa36027ce7619e7b9",
    )

    maven_jar(
        name = "aws_identity_spi",
        artifact = "software.amazon.awssdk:identity-spi:2.29.15",
        sha1 = "892d5b9476bdfcf0f7f177b45995d6cf17e5da2b",
    )

    maven_jar(
        name = "aws_utils",
        artifact = "software.amazon.awssdk:utils:2.29.15",
        sha1 = "9f4c25b1aef2c80eeba6c6a8e4b0eed6453224aa",
    )

    maven_jar(
        name = "aws_http_client_spi",
        artifact = "software.amazon.awssdk:http-client-spi:2.29.15",
        sha1 = "81f806d3aabf78b687895bdd11c4e9560bc029f2",
    )

    maven_jar(
        name = "aws_sns",
        artifact = "software.amazon.awssdk:sns:2.29.15",
        sha1 = "3271de1c86221da6ab2859dc21a7a5aad088456e",
    )

    maven_jar(
        name = "aws_core",
        artifact = "software.amazon.awssdk:aws-core:2.29.15",
        sha1 = "610fb7f6805c0b8aefd0375ae6ec8a8e25e7a999",
    )

    maven_jar(
        name = "aws_sdk_core",
        artifact = "software.amazon.awssdk:sdk-core:2.29.15",
        sha1 = "1925ab5140147caa628a8e60ebf5cceaafbd3bf8",
    )

    maven_jar(
        name = "aws_retries_spi",
        artifact = "software.amazon.awssdk:retries-spi:2.29.15",
        sha1 = "6140215780464bab2d0682b380962076800d8006",
    )

    maven_jar(
        name = "aws_profiles",
        artifact = "software.amazon.awssdk:profiles:2.29.15",
        sha1 = "b5c9fd6e7e84459f324a39465d12ed4de552bd4d",
    )

    maven_jar(
        name = "aws_endpoints_spi",
        artifact = "software.amazon.awssdk:endpoints-spi:2.29.15",
        sha1 = "4ed1811cf531ab3df0a8f54593360476773226f4",
    )

    maven_jar(
        name = "aws_http_auth_api",
        artifact = "software.amazon.awssdk:http-auth-spi:2.29.15",
        sha1 = "c9d8d747752f4b96eb2406f755be14f0dbe06dc0",
    )

    maven_jar(
        name = "netty-common",
        artifact = "io.netty:netty-common:4.1.115.Final",
        sha1 = "9da10a9f72e3f87e181d91b525174007a6fc4f11",
    )

    maven_jar(
        name = "netty-transport",
        artifact = "io.netty:netty-transport:4.1.115.Final",
        sha1 = "39cef77c1a25908ac1abf4960c2e789f0bf70ff9",
    )

    maven_jar(
        name = "aws_http_auth_aws",
        artifact = "software.amazon.awssdk:http-auth-aws:2.29.15",
        sha1 = "a70592df0038ac7c6110359d70305bc8ba0ead56",
    )

    maven_jar(
        name = "aws_http_auth",
        artifact = "software.amazon.awssdk:http-auth:2.29.15",
        sha1 = "41fb12e1f9d8df0a517d1fbbcbb462a95a2f6e5f",
    )

    maven_jar(
        name = "reactive-streams",
        artifact = "org.reactivestreams:reactive-streams:1.0.4",
        sha1 = "3864a1320d97d7b045f729a326e1e077661f31b7",
    )

    maven_jar(
        name = "aws_retries",
        artifact = "software.amazon.awssdk:retries:2.29.15",
        sha1 = "9ca1397bff09b2a14895de65ece16e0cb17a4b75",
    )

    maven_jar(
        name = "netty-handler",
        artifact = "io.netty:netty-handler:4.1.115.Final",
        sha1 = "d54dbf68b9d88a98240107758c6b63da5e46e23a",
    )

    maven_jar(
        name = "netty-buffer",
        artifact = "io.netty:netty-buffer:4.1.115.Final",
        sha1 = "d5daf1030e5c36d198caf7562da2441a97ec0df6",
    )

    maven_jar(
        name = "aws_metrics_spi",
        artifact = "software.amazon.awssdk:metrics-spi:2.29.15",
        sha1 = "88322ea9e46a644ca9381d92e443ff0bd50a5dc1",
    )

    maven_jar(
        name = "aws_query_protocol",
        artifact = "software.amazon.awssdk:aws-query-protocol:2.29.15",
        sha1 = "d51d6de32e2a9668487480e802539f5c78269d88",
    )

    maven_jar(
        name = "aws_protocol_core",
        artifact = "software.amazon.awssdk:protocol-core:2.29.15",
        sha1 = "419d3d7d727e22a9887dee85357174b4e22c05b2",
    )

    maven_jar(
        name = "aws_checksums",
        artifact = "software.amazon.awssdk:checksums:2.29.15",
        sha1 = "76c9516e0486ec2e7514737cbe3c362ab2f4e7d1",
    )

    maven_jar(
        name = "aws_checksums_spi",
        artifact = "software.amazon.awssdk:checksums-spi:2.29.15",
        sha1 = "59a335f971c7a4c9fe02721452eebc6fee263bb1",
    )

    maven_jar(
        name = "netty-codec-http2",
        artifact = "io.netty:netty-codec-http2:4.1.115.Final",
        sha1 = "0bc474c27c96e3a309da73160fbcfe0bd3aa85bc",
    )

    maven_jar(
        name = "netty-codec-http",
        artifact = "io.netty:netty-codec-http:4.1.115.Final",
        sha1 = "80f0dece29a2c0269217e8dd1b6db6ff9710781f",
    )

    maven_jar(
        name = "netty-codec",
        artifact = "io.netty:netty-codec:4.1.115.Final",
        sha1 = "d326bf3a4c785b272da3db6941779a1bd5448378",
    )

    maven_jar(
        name = "netty-resolver",
        artifact = "io.netty:netty-resolver:4.1.115.Final",
        sha1 = "e33b4d476c03975957f5d8d0319d592bf2bc5e96",
    )

    maven_jar(
        name = "aws_json_protocol",
        artifact = "software.amazon.awssdk:aws-json-protocol:2.29.15",
        sha1 = "77092a930c8c8dff7e1c42a28c044a5c53f9308e",
    )

    maven_jar(
        name = "aws_json_utils",
        artifact = "software.amazon.awssdk:json-utils:2.29.15",
        sha1 = "13de26806b6ad6093ed835dfb7e457d232357b12",
    )

    maven_jar(
        name = "aws_third_party_jackson_core",
        artifact = "software.amazon.awssdk:third-party-jackson-core:2.29.15",
        sha1 = "34f3f8357d790d683866358551b4642be77f4c24",
    )

    maven_jar(
        name = "aws_crt_client",
        artifact = "software.amazon.awssdk:aws-crt-client:2.29.15",
        sha1 = "ba23139f04b9b2e93db4ba37031a42e43bd1ef8b",
    )

    maven_jar(
        name = "aws_crt",
        artifact = "software.amazon.awssdk.crt:aws-crt:0.31.3",
        sha1 = "d2c19088e4fd2a24889a9b3d24b40ba967f64168",
    )
    maven_jar(
        name = "aws_crt_core",
        artifact = "software.amazon.awssdk:crt-core:2.29.15",
        sha1 = "893f6fd172ebff1553926a7edbae89ca241b0175",
    )
    maven_jar(
        name = "netty_transport_native_unix_common",
        artifact = "io.netty:netty-transport-native-unix-common:4.1.115.Final",
        sha1 = "dc96c67d06cd6b5eb677f2728f27bf2e3d9a7284",
    )

    DOCKER_JAVA_VERS = "3.6.0"

    maven_jar(
        name = "docker-java-api",
        artifact = "com.github.docker-java:docker-java-api:" + DOCKER_JAVA_VERS,
        sha1 = "caeb5bee6a9c07bff31f73ace576436168e2aa47",
    )

    maven_jar(
        name = "docker-java-transport",
        artifact = "com.github.docker-java:docker-java-transport:" + DOCKER_JAVA_VERS,
        sha1 = "d522c467aad17fd927e0db0130d2849a321a36aa",
    )

    maven_jar(
        name = "docker-java-transport-zerodep",
        artifact = "com.github.docker-java:docker-java-transport-zerodep:" + DOCKER_JAVA_VERS,
        sha1 = "549f4985f9c7714deff47d1041603e85e132d184",
    )

    TESTCONTAINERS_VERSION = "1.21.3"

    maven_jar(
        name = "testcontainers",
        artifact = "org.testcontainers:testcontainers:" + TESTCONTAINERS_VERSION,
        sha1 = "aa3e792d2cf4598019933c42f1cfa55bd608ce8b",
    )

    maven_jar(
        name = "testcontainers-aws-localstack",
        artifact = "org.testcontainers:localstack:" + TESTCONTAINERS_VERSION,
        sha1 = "86cd23aaba16741005c794d26419a16c8470a8e1",
    )

    maven_jar(
        name = "duct-tape",
        artifact = "org.rnorth.duct-tape:duct-tape:1.0.8",
        sha1 = "92edc22a9ab2f3e17c9bf700aaee377d50e8b530",
    )

    maven_jar(
        name = "jackson-annotations",
        artifact = "com.fasterxml.jackson.core:jackson-annotations:2.19.2",
        sha1 = "0c5381f11988ae3d424b197a26087d86067b6d7d",
    )

