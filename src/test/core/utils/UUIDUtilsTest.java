package test.core.utils;

import java.util.UUID;

import org.junit.Test;

public class UUIDUtilsTest {

	@Test
	public void testGetUUID() {
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
	}

}
