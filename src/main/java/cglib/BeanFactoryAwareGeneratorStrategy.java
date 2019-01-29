package cglib;

import org.springframework.asm.Type;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.cglib.core.ClassGenerator;
import org.springframework.cglib.core.Constants;
import org.springframework.cglib.core.DefaultGeneratorStrategy;
import org.springframework.cglib.transform.ClassEmitterTransformer;
import org.springframework.cglib.transform.TransformingClassGenerator;
import org.springframework.lang.Nullable;

/**
 * @Author: yyl
 * @Date: 2019/1/16 19:48
 */
public class BeanFactoryAwareGeneratorStrategy extends DefaultGeneratorStrategy {
    @Nullable
    private final ClassLoader classLoader;

    public BeanFactoryAwareGeneratorStrategy(@Nullable ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    protected ClassGenerator transform(ClassGenerator cg) throws Exception {
        ClassEmitterTransformer transformer = new ClassEmitterTransformer() {
            @Override
            public void end_class() {
                declare_field(Constants.ACC_PUBLIC, "$$beanFactory", Type.getType(BeanFactory.class), null);
                super.end_class();
            }
        };
        return new TransformingClassGenerator(cg, transformer);
    }

    @Override
    public byte[] generate(ClassGenerator cg) throws Exception {
        if (this.classLoader == null) {
            return super.generate(cg);
        }

        Thread currentThread = Thread.currentThread();
        ClassLoader threadContextClassLoader;
        try {
            threadContextClassLoader = currentThread.getContextClassLoader();
        }
        catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back...
            return super.generate(cg);
        }

        boolean overrideClassLoader = !this.classLoader.equals(threadContextClassLoader);
        if (overrideClassLoader) {
            currentThread.setContextClassLoader(this.classLoader);
        }
        try {
            return super.generate(cg);
        }
        finally {
            if (overrideClassLoader) {
                // Reset original thread context ClassLoader.
                currentThread.setContextClassLoader(threadContextClassLoader);
            }
        }
    }
}
