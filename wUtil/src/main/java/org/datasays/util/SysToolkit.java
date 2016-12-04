package org.datasays.util;

import jodd.io.FileUtil;
import jodd.io.FileUtilParams;
import jodd.io.StreamUtil;
import jodd.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.BasicFileAttributes;

public class SysToolkit {
	private static final Logger LOG = LoggerFactory.getLogger(SysToolkit.class);

	public static void exec(String cmd, String cwd, boolean showError, boolean showOut) throws Exception {
		Process p = Runtime.getRuntime().exec("cmd /c " + cmd, null, new File(cwd));
		if (showOut) {
			StringWriter sw = new StringWriter();
			StreamUtil.copy(p.getInputStream(), sw, "gbk");
			String out = sw.toString().trim();
			if (out.length() > 0) {
				LOG.info(out);
			}
		}
		if (showError) {
			StringWriter sw = new StringWriter();
			StreamUtil.copy(p.getErrorStream(), sw, "gbk");
			String error = sw.toString().trim();
			if (error.length() > 0) {
				LOG.error(cmd + "\n" + error);
				throw new Exception(error);
			}
		}
	}

	public static boolean isSymbolicLink(String path) throws Exception {
		BasicFileAttributes attrs = Files.readAttributes(FileSystems.getDefault().getPath(polishFilePath(path)), BasicFileAttributes.

				class, LinkOption.NOFOLLOW_LINKS);
		return attrs.isSymbolicLink();
	}

	public static void mklink(String target, String source) throws Exception {
		File fTarget = new File(polishFilePath(target));
		if (fTarget.exists() && fTarget.isDirectory()) {
			if (isSymbolicLink(target)) {
				exec("rd /q " + fTarget.getAbsolutePath(), ".", true, true);
			} else {
				FileUtil.deleteDir(fTarget);
			}
		}
		if (!fTarget.getParentFile().exists()) {
			FileUtil.mkdirs(fTarget.getParentFile());
		}
		File fSource = new File(polishFilePath(source));
		if (fSource.isDirectory()) {
			exec("mklink /d " + target + " " + source, ".", true, true);
		} else {
			exec("mklink /h " + target + " " + source, ".", true, true);
		}
	}

	public

	static void copyDir(String srcDir, String destDir) throws Exception {
		FileUtil.copyDir(polishFilePath(srcDir), polishFilePath(destDir));
	}

	public

	static void copyFile(String srcDir, String destDir) throws Exception {
		FileUtil.copyFile(polishFilePath(srcDir), polishFilePath(destDir));
	}

	public

	static String polishFilePath(String path) {
		String path2 = StringUtil.replace(path, "\\", File.separator);
		path2 = StringUtil.replace(path2, "/", File.separator);
		return path2;
	}

	public static void delFiles(String file) throws Exception {
		File f = new File(polishFilePath(file));
		if (f.exists()) {
			LOG.info("删除" + file);
			if (f.isDirectory()) {
				if (isSymbolicLink(file)) {
					exec("rd /q " + f.getAbsolutePath() + "", ".", true, true);
				} else {
					FileUtil.deleteDir(f.getAbsolutePath(), new FileUtilParams().setRecursive(true).setContinueOnError(false));
				}
			} else {
				FileUtil.delete(f.getAbsolutePath(), new FileUtilParams().setContinueOnError(false));
			}
		}
	}
}
