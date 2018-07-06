package ua.com.foxminded.di;

import com.sun.istack.internal.NotNull;

import java.util.HashMap;

public class Context {
    private HashMap<String, Object> context;
    private HashMap<Class<?>, Context.Scope> contextWithScope;

    public Context() {
    }

    @NotNull
    public HashMap<String, Object> getBean() {
        return context;
    }

    public Object getBeanWithScope(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        if(contextWithScope.get(clazz.getClass()) == Scope.PROTOTYPE ){
            return clazz.getClass().newInstance();
        }
        return null;
    }

    public void registerBean(String string, Object obj) {
        if (context == null) {
            context = new HashMap<String, Object>();
        }
        this.context.put(string, obj);
    }

    public void registerBean(Class<?> clazz, Context.Scope scope) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        if (contextWithScope == null) {
            contextWithScope = new HashMap<>();
        }
        if (scope == Scope.PROTOTYPE) {
            contextWithScope.put(clazz, scope);

        } else {
            contextWithScope.put(clazz, scope);
        }
    }

    public enum Scope {SINGLTON, PROTOTYPE};
}
