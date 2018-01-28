package toybox.utils.blocks;

import java.util.concurrent.locks.Lock;
import java.util.function.Consumer;

public final class Blocks {

    public static <T> void withSynchronization(final T monitor, Consumer<? super T> block) throws Exception {
        synchronized (monitor) {
            block.accept(monitor);
        }
    }

    public static void withCloseable(final AutoCloseable resource, Consumer<? super AutoCloseable> block) throws Exception {
        try {
            block.accept(resource);
        } finally {
            resource.close();
        }
    }

    public static void withLock(final Lock lock, Consumer<? super Lock> block) throws Exception {
        lock.lock();
        try {
            block.accept(lock);
        } finally {
            lock.unlock();
        }
    }

    public static void withLock(final Lock lock, Runnable block) throws Exception {
        lock.lock();
        try {
            block.run();
        } finally {
            lock.unlock();
        }
    }
}
