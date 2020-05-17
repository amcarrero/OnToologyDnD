package com.ontoology.util;

import java.nio.file.Paths;

public class Configuration {

	public static String WIDOCO_DIR = Paths.get("libraries//widoco.jar").toAbsolutePath().toString();
	public static String AR2DTOOL_DIR = Paths.get("libraries//ar2dtool.jar").toAbsolutePath().toString();
	public static String AR2DTOOL_CONF1 = Paths.get("libraries//ar2dtool-class.conf").toAbsolutePath().toString();
	public static String AR2DTOOL_CONF2 = Paths.get("libraries//ar2dtool-taxonomy.conf").toAbsolutePath().toString();
	public static String OOPS_DIR = Paths.get("libraries//oops.zip").toAbsolutePath().toString();

}
