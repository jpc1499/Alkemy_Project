package com.juancerezole.alkemy.apirest.backend.Auth;

public class JwtConfig {

	public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";
	
	public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEpQIBAAKCAQEAlHFcLsNzDZP2m/H/6sVMQkbRpKu3H5xDns1/uswUYXE1q1nF\r\n"
			+ "vF9yTUG6kxZSQqI5oiMxZxvVVpGh8b/HIJArfzHHsneZHz1VysMBimbQTKcUdjTY\r\n"
			+ "fx1sxfx4sc09koxwSoUzDzWrNgdZQ69z+2oBOkA15XvG5rKO6kCZgOpNQnmOkl0Q\r\n"
			+ "rMC/2lvZDHTEWCMsAEFDZnPlO3W84LRGgyNdhZq0qA8oLg2g8p2rWH2da3ITscSI\r\n"
			+ "+wkm7cOSLhMHUzXT8eBrpgUlJUX9K8NE7uGtxd3Ilg+SbYmvPF7qqBJQwejb1rpR\r\n"
			+ "Kf30MydlYCLJyc0gyUlnOr4IGD3a1ckHyDJ45QIDAQABAoIBAQCS/ITpEUPVyBPD\r\n"
			+ "ayeb0u2mGvXv9tCHjnYuwxGdaAMuUalxGJ2B34kREuqqRsoBjR1AfVSn4pmm9OHE\r\n"
			+ "vb9xcRCBDEvPXjnTAyr2cY4O+6t6m17ZhY5yoDJbAZqJhM9ZU5LFV4utndkA0NoE\r\n"
			+ "+bEA3ICGq5oyuBPF0D1Pm9PkIR2LmcwHJdWd2q1l5OYCEaszllQElPrPOoeF41Mf\r\n"
			+ "myzUIQGkGQeY7m/q+VEeFKScXK29K/IAiOecfXrzq3YqZDm9XEiPI3QNDNSe1GTs\r\n"
			+ "2A2ChQ/pC6cfAgbTbpNVOTZzp725diGG7cvQfg7NgMOW8YU0WgfjaIQttGTCbdyc\r\n"
			+ "NdLpDRABAoGBAMXSye/ZANmrGO/QRwEdmUQxxj4yHfmFBXHqL7g1/40HVpoPR7Rn\r\n"
			+ "SHeyvY4Ws5hXlbJbfo0ty+srsqYk3jI7IOTJH+NzVGAnHWXnxxSkd7uIODtnIg9U\r\n"
			+ "Zvn+Ut498+1SjCZBKlUkv40yusyFYHlboNdvMRWb2Mi82MTdd542e9LlAoGBAMAY\r\n"
			+ "711f5xUylvYdgy4reJbGW6cmQUUsXzMVtHgF932RMunKa+lcPMMYSxbWsCQTwGwM\r\n"
			+ "qHaf3FdUDqBxyLPK+4+btM2YOIG5zii5h/6LFY4slzcQS4sTr6Pox6tBmJJ4HMxR\r\n"
			+ "hANBGKD6QY/WcF7ISw5GgMgBkB1lZ5EnKnbF864BAoGAbZe4XdLXgL8kAn9dRsXy\r\n"
			+ "CfCVRhGXv4stGA3IQAJSP/wHwzOh4tSxNfbkwpnbBxPGj9fWRTpQ1UkRILAeFK/j\r\n"
			+ "KjwuGWDkJpyk0bbU+J+g4Vi25lndaaa1odt+ZzblMt3fXLYPULoaEIxBJlrhtnnH\r\n"
			+ "onIsPC9pqtPMN0wGH7VJZe0CgYEAml4vVJDxyQYLbCIRPbPvRvKFyo+iWe0rX4js\r\n"
			+ "qprY5I4fJOLmzvOxXHIUPXkuuP8qXWnaouEaFw9UZbiqOVk/yIquw5y0pALlWbKm\r\n"
			+ "R3YLT2Ij7jrpCg060vQIKGYhLmrZcZN8C4VvPkV9FBKV4IUCU6lyacdfwU+w52Na\r\n"
			+ "63FtvgECgYEAjyroCuFLuUMfikjzWGtSyjkEEzlfc0RJUiIH1C6xHomffuelGDVH\r\n"
			+ "B5sQYoHjO8Ah3ngPbOkOE1W2EcqF7yClaf1uXnuxm0tBVUXej2mvrN67cQxFTPrI\r\n"
			+ "oiWvD9MFg28sQFsF2lcDr1CdYMAd+HySUlRHV48DLP1qaA2nr8GcTgI=\r\n"
			+ "-----END RSA PRIVATE KEY-----";
			
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlHFcLsNzDZP2m/H/6sVM\r\n"
			+ "QkbRpKu3H5xDns1/uswUYXE1q1nFvF9yTUG6kxZSQqI5oiMxZxvVVpGh8b/HIJAr\r\n"
			+ "fzHHsneZHz1VysMBimbQTKcUdjTYfx1sxfx4sc09koxwSoUzDzWrNgdZQ69z+2oB\r\n"
			+ "OkA15XvG5rKO6kCZgOpNQnmOkl0QrMC/2lvZDHTEWCMsAEFDZnPlO3W84LRGgyNd\r\n"
			+ "hZq0qA8oLg2g8p2rWH2da3ITscSI+wkm7cOSLhMHUzXT8eBrpgUlJUX9K8NE7uGt\r\n"
			+ "xd3Ilg+SbYmvPF7qqBJQwejb1rpRKf30MydlYCLJyc0gyUlnOr4IGD3a1ckHyDJ4\r\n"
			+ "5QIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";
}
