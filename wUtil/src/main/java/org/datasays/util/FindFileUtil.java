package org.datasays.util;

import jodd.io.findfile.FindFile;
import jodd.io.findfile.WildcardFindFile;

import java.io.File;
import java.util.Iterator;

public class FindFileUtil {
	public static Iterator<File> search(boolean recursive, boolean includeDirs, String dir) {
		FindFile<WildcardFindFile> ff = create(recursive, includeDirs).searchPath(dir);
		Iterator<File> iterator = ff.iterator();
		return iterator;
	}

	public static Iterator<File> search(boolean recursive, boolean includeDirs, String dir, String fileName) {
		FindFile<WildcardFindFile> ff = create(recursive, includeDirs).include(fileName).searchPath(dir);
		return ff.iterator();
	}

	public static Iterator<File> search(boolean recursive, boolean includeDirs, File dir) {
		FindFile<WildcardFindFile> ff = create(recursive, includeDirs).searchPath(dir);
		Iterator<File> iterator = ff.iterator();
		return iterator;
	}

	public static Iterator<File> search(boolean recursive, boolean includeDirs, File dir, String fileName) {
		FindFile<WildcardFindFile> ff = create(recursive, includeDirs).include(fileName).searchPath(dir);
		return ff.iterator();
	}

	public static FindFile<WildcardFindFile> create(boolean recursive, boolean includeDirs) {
		return new WildcardFindFile().setRecursive(recursive).setIncludeDirs(includeDirs).setIncludeFiles(true);
	}
}
