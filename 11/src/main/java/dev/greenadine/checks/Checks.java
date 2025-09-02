/*
 * ${project.parent.name}
 * Copyright (C) 2025 Greenadine (https://greenadine.dev)
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package dev.greenadine.checks;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A utility class that provides a set of static methods for performing various checks and validations throughout a Java application.
 *
 * @author Greenadine
 */
public final class Checks {

    private Checks() {
    }

    ///////////////////////////////////////////////////////////////////////////
    //// General
    ///////////////////////////////////////////////////////////////////////////

    //<editor-fold defaultstate="collapsed" desc="General">

    /**
     * Ensures the truth of an expression, and throws an {@link IllegalArgumentException} if the check fails.
     *
     * @param expression the expression to check.
     * @throws IllegalArgumentException if the expression is {@code false}.
     */
    @Contract("false -> fail")
    public static void check(boolean expression) {
        if (!expression)
            throw new IllegalArgumentException();
    }

    /**
     * Ensures the truth of an expression, and throws an {@link IllegalArgumentException} with the specified exception message if the check fails.
     *
     * @param expression the expression to check.
     * @param message    the exception message to use if the check fails.
     * @throws IllegalArgumentException if the expression is {@code false}.
     */
    @Contract("false, _ -> fail")
    public static void check(boolean expression, String message) {
        if (!expression)
            throw new IllegalArgumentException(message);
    }

    /**
     * Ensures the truth of an expression, and throws an {@link IllegalArgumentException} with the specified exception message and replacements if the check fails.
     *
     * @param expression   the expression to check.
     * @param message      the exception message to use if the check fails.
     * @param replacements the replacements to use in the exception message.
     * @throws IllegalArgumentException if the expression is {@code false}.
     */
    @Contract("false, _, _ -> fail")
    public static void check(boolean expression, @NotNull String message, Object... replacements) {
        if (!expression)
            throw new IllegalArgumentException(String.format(message, replacements));
    }

    /**
     * Ensures the truth of an expression, and throws an exception if the check fails.
     *
     * @param expression        the expression to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param <T>               the type of the exception to throw.
     * @throws T if the expression is {@code false}.
     */
    @Contract("false, _ -> fail")
    public static <T extends Throwable> void check(boolean expression, @NotNull Supplier<T> throwableProvider) throws T {
        if (!expression)
            throw throwableProvider.get();
    }

    /**
     * Ensures the truth of an expression, and throws an exception if the check fails.
     *
     * @param expression        the expression to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param message           the message to use in the exception.
     * @param <T>               the type of the exception to throw.
     * @throws T if the expression is {@code false}.
     */
    @Contract("false, _, _ -> fail")
    public static <T extends Throwable> void check(boolean expression, @NotNull Function<String, T> throwableProvider, String message) throws T {
        if (!expression)
            throw throwableProvider.apply(message);
    }

    /**
     * Ensures the truth of an expression, and throws an exception if the check fails. The type of thrown exception can be specified.
     *
     * @param expression        the expression to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param message           the message to use in the exception.
     * @param replacements      the replacements to use in the message.
     * @param <T>               the type of the exception to throw.
     * @throws T if the expression is {@code false}.
     */
    @Contract("false, _, _, _ -> fail")
    public static <T extends Throwable> void check(boolean expression, @NotNull BiFunction<String, Object[], T> throwableProvider, @NotNull String message, Object... replacements) throws T {
        if (!expression)
            throw throwableProvider.apply(message, replacements);
    }

    /**
     * Ensures the truth of an expression, and throws an exception if the check fails.
     *
     * @param expression        the expression to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg               the argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the argument to pass to the exception provider.
     * @throws T if the expression is {@code false}.
     */
    @Contract("false, _, _ -> fail")
    public static <T extends Throwable, U> void check(boolean expression, @NotNull Function<U, T> throwableProvider, U arg) throws T {
        if (!expression)
            throw throwableProvider.apply(arg);
    }

    /**
     * Ensures the truth of an expression, and throws an exception if the check fails.
     *
     * @param expression        the expression to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg1              the first argument to pass to the exception provider.
     * @param arg2              the second argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the first argument to pass to the exception provider.
     * @param <V>               the type of the second argument to pass to the exception provider.
     * @throws T if the expression is {@code false}.
     */
    @Contract("false, _, _, _ -> fail")
    public static <T extends Throwable, U, V> void check(boolean expression, @NotNull BiFunction<U, V, T> throwableProvider, U arg1, V arg2) throws T {
        if (!expression)
            throw throwableProvider.apply(arg1, arg2);
    }

    /**
     * Ensures the truth of an expression, and throws an exception if the check fails.
     *
     * @param expression        the expression to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg1              the first argument to pass to the exception provider.
     * @param arg2              the second argument to pass to the exception provider.
     * @param arg3              the third argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the first argument to pass to the exception provider.
     * @param <V>               the type of the second argument to pass to the exception provider.
     * @param <R>               the type of the third argument to pass to the exception provider.
     * @throws T if the expression is {@code false}.
     */
    @Contract("false, _, _, _, _ -> fail")
    public static <T extends Throwable, U, V, R> void check(boolean expression, @NotNull TriFunction<U, V, R, T> throwableProvider, U arg1, V arg2, R arg3) throws T {
        if (!expression)
            throw throwableProvider.apply(arg1, arg2, arg3);
    }
    //</editor-fold>

    ///////////////////////////////////////////////////////////////////////////
    //// State
    ///////////////////////////////////////////////////////////////////////////
    //<editor-fold defaultstate="collapsed" desc="State">

    /**
     * Ensures the truth of an expression, and throws an {@link IllegalStateException} if the check fails.
     *
     * @param expression the expression to check.
     * @throws IllegalStateException if the expression is {@code false}.
     */
    @Contract("false -> fail")
    public static void checkState(boolean expression) {
        if (!expression)
            throw new IllegalStateException();
    }

    /**
     * Ensures the truth of an expression, and throws an {@link IllegalStateException} with the specified exception message if the check fails.
     *
     * @param expression the expression to check.
     * @param message    the exception message to use if the check fails.
     * @throws IllegalStateException if the expression is {@code false}.
     */
    @Contract("false, _ -> fail")
    public static void checkState(boolean expression, String message) {
        if (!expression)
            throw new IllegalStateException(message);
    }

    /**
     * Ensures the truth of an expression, and throws an {@link IllegalStateException} with the specified exception message and replacements if the check fails.
     *
     * @param expression   the expression to check.
     * @param message      the exception message to use if the check fails.
     * @param replacements the replacements to use in the exception message.
     * @throws IllegalStateException if the expression is {@code false}.
     */
    @Contract("false, _, _ -> fail")
    public static void checkState(boolean expression, @NotNull String message, Object... replacements) {
        if (!expression)
            throw new IllegalStateException(String.format(message, replacements));
    }

    //</editor-fold>

    ///////////////////////////////////////////////////////////////////////////
    //// Nullability
    ///////////////////////////////////////////////////////////////////////////

    //<editor-fold desc="Nullability">

    //<editor-fold desc="Null">
    /**
     * Ensures that an object reference passed as a parameter to the calling method is {@code null}, and throws an {@link IllegalArgumentException} if it is not.
     *
     * @param argument the object to check.
     * @throws IllegalArgumentException if the argument is not {@code null}.
     */
    @Contract("!null -> fail")
    public static void isNull(Object argument) {
        if (argument != null)
            throw new IllegalArgumentException();
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is {@code null}, and throws an {@link IllegalArgumentException} with the specified exception message if it is not.
     *
     * @param argument the object to check.
     * @param message  the exception message to use if the check fails.
     * @throws IllegalArgumentException if the argument is not {@code null}.
     */
    @Contract("!null, _ -> fail")
    public static void isNull(Object argument, String message) {
        if (argument != null)
            throw new IllegalArgumentException(message);
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is {@code null}, and throws an {@link IllegalArgumentException} with the specified exception message and replacements if it is not.
     *
     * @param argument     the object to check.
     * @param message      the exception message to use if the check fails.
     * @param replacements the replacements to use in the exception message.
     * @throws IllegalArgumentException if the argument is not {@code null}.
     */
    @Contract("!null, _, _ -> fail")
    public static void isNull(Object argument, @NotNull String message, Object... replacements) {
        if (argument != null)
            throw new IllegalArgumentException(String.format(message, replacements));
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is {@code null}, and throws an exception if it is.
     *
     * @param argument          the object to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param <T>               the type of the exception to throw.
     * @throws T if the argument is not {@code null}.
     */
    @Contract("!null, _ -> fail")
    public static <T extends Throwable> void isNull(Object argument, @NotNull Supplier<T> throwableProvider) throws T {
        if (argument != null)
            throw throwableProvider.get();
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is {@code null}, and throws an exception if it is.
     *
     * @param argument          the object to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param message           the message to use in the exception.
     * @param <T>               the type of the exception to throw.
     * @throws T if the argument is not {@code null}.
     */
    @Contract("!null, _, _ -> fail")
    public static <T extends Throwable> void isNull(Object argument, @NotNull Function<String, T> throwableProvider, String message) throws T {
        if (argument != null)
            throw throwableProvider.apply(message);
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is {@code null}, and throws an exception if it is. The type of thrown exception can be specified.
     *
     * @param argument          the object to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param message           the message to use in the exception.
     * @param replacements      the replacements to use in the message.
     * @param <T>               the type of the exception to throw.
     * @throws T if the argument is not {@code null}.
     */
    @Contract("!null, _, _, _ -> fail")
    public static <T extends Throwable> void isNull(Object argument, @NotNull BiFunction<String, Object[], T> throwableProvider, String message, Object... replacements) throws T {
        if (argument != null)
            throw throwableProvider.apply(message, replacements);
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is {@code null}, and throws an exception if it is.
     *
     * @param argument          the object to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg               the argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the argument to pass to the exception provider.
     * @throws T if the argument is not {@code null}.
     */
    @Contract("!null, _, _ -> fail")
    public static <T extends Throwable, U> void isNull(Object argument, @NotNull Function<U, T> throwableProvider, U arg) throws T {
        if (argument != null)
            throw throwableProvider.apply(arg);
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is {@code null}, and throws an exception if it is.
     *
     * @param argument          the object to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg1              the first argument to pass to the exception provider.
     * @param arg2              the second argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the first argument to pass to the exception provider.
     * @param <V>               the type of the second argument to pass to the exception provider.
     * @throws T if the argument is not {@code null}.
     */
    @Contract("!null, _, _, _ -> fail")
    public static <T extends Throwable, U, V> void isNull(Object argument, @NotNull BiFunction<U, V, T> throwableProvider, U arg1, V arg2) throws T {
        if (argument != null)
            throw throwableProvider.apply(arg1, arg2);
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is {@code null}, and throws an exception if it is.
     *
     * @param argument          the object to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg1              the first argument to pass to the exception provider.
     * @param arg2              the second argument to pass to the exception provider.
     * @param arg3              the third argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the first argument to pass to the exception provider.
     * @param <V>               the type of the second argument to pass to the exception provider.
     * @param <R>               the type of the third argument to pass to the exception provider.
     * @throws T if the argument is not {@code null}.
     */
    @Contract("!null, _, _, _, _ -> fail")
    public static <T extends Throwable, U, V, R> void isNull(Object argument, @NotNull TriFunction<U, V, R, T> throwableProvider, U arg1, V arg2, R arg3) throws T {
        if (argument != null)
            throw throwableProvider.apply(arg1, arg2, arg3);
    }
    //</editor-fold>

    //<editor-fold desc="NotNull">
    /**
     * Ensures that an object reference passed as a parameter to the calling method is not {@code null}, and throws a {@link NullPointerException} if it is.
     *
     * @param argument the object to check.
     * @throws NullPointerException if the argument is {@code null}.
     */
    @Contract("null -> fail")
    public static void isNotNull(Object argument) {
        if (argument == null)
            throw new NullPointerException();
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null, and throws a {@link NullPointerException} with the specified exception message if it is.
     *
     * @param argument the object to check.
     * @param message  the exception message to use if the check fails.
     * @throws NullPointerException if the argument is {@code null}.
     */
    @Contract("null, _ -> fail")
    public static void isNotNull(Object argument, String message) {
        if (argument == null)
            throw new NullPointerException(message);
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null, and throws a {@link NullPointerException} with the specified exception message and replacements if
     * it is.
     *
     * @param argument     the object to check.
     * @param message      the exception message to use if the check fails.
     * @param replacements the replacements to use in the exception message.
     */
    @Contract("null, _, _ -> fail")
    public static void isNotNull(Object argument, @NotNull String message, Object... replacements) {
        if (argument == null)
            throw new NullPointerException(String.format(message, replacements));
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null, and throws an exception if it is.
     *
     * @param argument          the object to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param <T>               the type of the exception to throw.
     * @throws T if the object is {@code null}.
     */
    @Contract("null, _ -> fail")
    public static <T extends Throwable> void isNotNull(Object argument, @NotNull Supplier<T> throwableProvider) throws T {
        if (argument == null)
            throw throwableProvider.get();
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not {@code null}, and throws an exception if it is.
     *
     * @param argument          the object to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param message           the message to use in the exception.
     * @param <T>               the type of the exception to throw.
     * @throws T if the object is {@code null}.
     */
    @Contract("null, _, _ -> fail")
    public static <T extends Throwable> void isNotNull(Object argument, @NotNull Function<String, T> throwableProvider, String message) throws T {
        if (argument == null)
            throw throwableProvider.apply(message);
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not {@code null}, and throws an exception if it is. The type of thrown exception can be specified.
     *
     * @param argument          the object to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param message           the message to use in the exception.
     * @param replacements      the replacements to use in the message.
     * @param <T>               the type of the exception to throw.
     * @throws T if the object is {@code null}.
     */
    @Contract("null, _, _, _ -> fail")
    public static <T extends Throwable> void isNotNull(Object argument, @NotNull BiFunction<String, Object[], T> throwableProvider, String message, Object... replacements) throws T {
        if (argument == null)
            throw throwableProvider.apply(message, replacements);
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not {@code null}, and throws an exception if it is.
     *
     * @param argument          the object to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg               the argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the argument to pass to the exception provider.
     * @throws T if the object is {@code null}.
     */
    @Contract("null, _, _ -> fail")
    public static <T extends Throwable, U> void isNotNull(Object argument, @NotNull Function<U, T> throwableProvider, U arg) throws T {
        if (argument == null)
            throw throwableProvider.apply(arg);
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not {@code null}, and throws an exception if it is.
     *
     * @param argument          the object to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg1              the first argument to pass to the exception provider.
     * @param arg2              the second argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the first argument to pass to the exception provider.
     * @param <V>               the type of the second argument to pass to the exception provider.
     * @throws T if the object is {@code null}.
     */
    @Contract("null, _, _, _ -> fail")
    public static <T extends Throwable, U, V> void isNotNull(Object argument, @NotNull BiFunction<U, V, T> throwableProvider, U arg1, V arg2) throws T {
        if (argument == null)
            throw throwableProvider.apply(arg1, arg2);
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null, and throws an exception if it is.
     *
     * @param argument          the object to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg1              the first argument to pass to the exception provider.
     * @param arg2              the second argument to pass to the exception provider.
     * @param arg3              the third argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the first argument to pass to the exception provider.
     * @param <V>               the type of the second argument to pass to the exception provider.
     * @param <R>               the type of the third argument to pass to the exception provider.
     * @throws T if the object is {@code null}.
     */
    @Contract("null, _, _, _, _ -> fail")
    public static <T extends Throwable, U, V, R> void isNotNull(Object argument, @NotNull TriFunction<U, V, R, T> throwableProvider, U arg1, V arg2, R arg3) throws T {
        if (argument == null)
            throw throwableProvider.apply(arg1, arg2, arg3);
    }
    //</editor-fold>
    //</editor-fold>

    ///////////////////////////////////////////////////////////////////////////
    //// Equality
    ///////////////////////////////////////////////////////////////////////////

    //<editor-fold desc="Equality">

    /**
     * Ensures that the given object is equal to the expected object, and throws an {@link IllegalArgumentException} if it is not.
     *
     * @param object   the object to check.
     * @param expected the expected object to compare against.
     * @throws IllegalArgumentException if the object is not equal to the expected object.
     */
    public static void equals(Object object, Object expected) {
        if (!object.equals(expected))
            throw new IllegalArgumentException("Expected " + expected + " but got " + object);
    }

    /**
     * Ensures that the given object is equal to the expected object, and throws an {@link IllegalArgumentException} with the specified exception message if it is not.
     *
     * @param object   the object to check.
     * @param expected the expected object to compare against.
     * @param message  the exception message to use if the check fails.
     * @throws IllegalArgumentException if the object is not equal to the expected object.
     */
    public static void equals(Object object, Object expected, String message) {
        if (!object.equals(expected))
            throw new IllegalArgumentException(message);
    }

    /**
     * Ensures that the given object is equal to the expected object, and throws an {@link IllegalArgumentException} with the specified exception message and replacements if it is not.
     *
     * @param object       the object to check.
     * @param expected     the expected object to compare against.
     * @param message      the exception message to use if the check fails.
     * @param replacements the replacements to use in the exception message.
     * @throws IllegalArgumentException if the object is not equal to the expected object.
     */
    public static void equals(Object object, Object expected, @NotNull String message, Object... replacements) {
        if (!object.equals(expected))
            throw new IllegalArgumentException(String.format(message, replacements));
    }

    /**
     * Ensures that the given object is equal to the expected object, and throws an exception if it is not.
     *
     * @param object            the object to check.
     * @param expected          the expected object to compare against.
     * @param throwableProvider the function that provides the exception to throw.
     * @param <T>               the type of the exception to throw.
     * @throws T if the object is not equal to the expected object.
     */
    public static <T extends Throwable> void equals(Object object, Object expected, @NotNull Supplier<T> throwableProvider) throws T {
        if (!object.equals(expected))
            throw throwableProvider.get();
    }

    /**
     * Ensures that the given object is equal to the expected object, and throws an exception if it is not.
     *
     * @param object            the object to check.
     * @param expected          the expected object to compare against.
     * @param throwableProvider the function that provides the exception to throw.
     * @param message           the message to use in the exception.
     * @param <T>               the type of the exception to throw.
     * @throws T if the object is not equal to the expected object.
     */
    public static <T extends Throwable> void equals(Object object, Object expected, @NotNull Function<String, T> throwableProvider, String message) throws T {
        if (!object.equals(expected))
            throw throwableProvider.apply(message);
    }

    /**
     * Ensures that the given object is equal to the expected object, and throws an exception if it is not. The type of thrown exception can be specified.
     *
     * @param object            the object to check.
     * @param expected          the expected object to compare against.
     * @param throwableProvider the function that provides the exception to throw.
     * @param message           the message to use in the exception.
     * @param replacements      the replacements to use in the message.
     * @param <T>               the type of the exception to throw.
     * @throws T if the object is not equal to the expected object.
     */
    public static <T extends Throwable> void equals(Object object, Object expected, @NotNull BiFunction<String, Object[], T> throwableProvider, String message, Object... replacements) throws T {
        if (!object.equals(expected))
            throw throwableProvider.apply(message, replacements);
    }

    /**
     * Ensures that the given object is equal to the expected object, and throws an exception if it is not.
     *
     * @param object            the object to check.
     * @param expected          the expected object to compare against.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg               the argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the argument to pass to the exception provider.
     * @throws T if the object is not equal to the expected object.
     */
    public static <T extends Throwable, U> void equals(Object object, Object expected, @NotNull Function<U, T> throwableProvider, U arg) throws T {
        if (!object.equals(expected))
            throw throwableProvider.apply(arg);
    }

    /**
     * Ensures that the given object is equal to the expected object, and throws an exception if it is not.
     *
     * @param object            the object to check.
     * @param expected          the expected object to compare against.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg1              the first argument to pass to the exception provider.
     * @param arg2              the second argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the first argument to pass to the exception provider.
     * @param <V>               the type of the second argument to pass to the exception provider.
     * @throws T if the object is not equal to the expected object.
     */
    public static <T extends Throwable, U, V> void equals(Object object, Object expected, @NotNull BiFunction<U, V, T> throwableProvider, U arg1, V arg2) throws T {
        if (!object.equals(expected))
            throw throwableProvider.apply(arg1, arg2);
    }

    /**
     * Ensures that the given object is equal to the expected object, and throws an exception if it is not.
     *
     * @param object            the object to check.
     * @param expected          the expected object to compare against.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg1              the first argument to pass to the exception provider.
     * @param arg2              the second argument to pass to the exception provider.
     * @param arg3              the third argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the first argument to pass to the exception provider.
     * @param <V>               the type of the second argument to pass to the exception provider.
     * @param <R>               the type of the third argument to pass to the exception provider.
     * @throws T if the object is not equal to the expected object.
     */
    public static <T extends Throwable, U, V, R> void equals(Object object, Object expected, @NotNull TriFunction<U, V, R, T> throwableProvider, U arg1, V arg2, R arg3) throws T {
        if (!object.equals(expected))
            throw throwableProvider.apply(arg1, arg2, arg3);
    }
    //</editor-fold>

    ///////////////////////////////////////////////////////////////////////////
    //// Instanceof
    ///////////////////////////////////////////////////////////////////////////

    //<editor-fold desc="Instanceof">

    /**
     * Ensures that the given object is {@linkplain Class#isAssignableFrom(Class) an instance of} the specified type, and throws an {@link IllegalArgumentException} if it is not. If the object
     * is an instance of the specified type, it is cast to that type and returned.
     *
     * @param object the object to check.
     * @param type   the class type to check against.
     * @param <T>    the type to check against.
     * @return the object cast to the specified type.
     * @throws IllegalArgumentException if the object is not an instance of the specified type.
     */
    public static <T> T instanceOf(Object object, @NotNull Class<T> type) {
        if (!type.isAssignableFrom(object.getClass()))
            throw new IllegalArgumentException("Argument is not an instance of " + type.getName());
        //noinspection unchecked
        return (T) object;
    }

    /**
     * Ensures that the given object is {@linkplain Class#isAssignableFrom(Class) an instance of} the specified type, and throws an {@link IllegalArgumentException} with the specified error
     * message if it is not. If the object is an instance of the specified type, it is cast to that type and returned.
     *
     * @param object  the object to check.
     * @param type    the class type to check against.
     * @param message the exception message to use if the check fails.
     * @param <T>     the type to check against.
     * @return the object cast to the specified type.
     * @throws IllegalArgumentException if the object is not an instance of the specified type.
     */
    public static <T> T instanceOf(Object object, @NotNull Class<T> type, String message) {
        if (!type.isAssignableFrom(object.getClass()))
            throw new IllegalArgumentException(message);
        //noinspection unchecked
        return (T) object;
    }

    /**
     * Ensures that the given object is {@linkplain Class#isAssignableFrom(Class) an instance of} the specified type, and throws an {@link IllegalArgumentException} with the specified error
     * message and replacements if it is not. If the object is an instance of the specified type, it is cast to that type and returned.
     *
     * @param object       the object to check.
     * @param type         the class type to check against.
     * @param message      the exception message to use if the check fails.
     * @param replacements the replacements to use in the exception message.
     * @param <T>          the type to check against.
     * @return the object cast to the specified type.
     * @throws IllegalArgumentException if the object is not an instance of the specified type.
     */
    public static <T> T instanceOf(Object object, @NotNull Class<T> type, String message, Object... replacements) {
        if (!type.isAssignableFrom(object.getClass()))
            throw new IllegalArgumentException(String.format(message, replacements));
        //noinspection unchecked
        return (T) object;
    }

    /**
     * Ensures that the given object is {@linkplain Class#isAssignableFrom(Class) an instance of} the specified type, and throws an exception if it is not. If the object is an instance of the
     * specified type, it is cast to that type and returned.
     *
     * @param object            the object to check.
     * @param type              the class type to check against.
     * @param throwableProvider the function that provides the exception to throw.
     * @param <T>               the type to check against.
     * @param <U>               the type of the exception to throw.
     * @return the object cast to the specified type.
     * @throws U if the object is not an instance of the specified type.
     */
    public static <T, U extends Throwable> T instanceOf(Object object, @NotNull Class<T> type, @NotNull Supplier<U> throwableProvider) throws U {
        if (!type.isAssignableFrom(object.getClass()))
            throw throwableProvider.get();
        //noinspection unchecked
        return (T) object;
    }

    /**
     * Ensures that the given object is {@linkplain Class#isAssignableFrom(Class) an instance of} the specified type, and throws an exception if it is not. If the object is an instance of the
     * specified type, it is cast to that type and returned.
     *
     * @param object            the object to check.
     * @param type              the class type to check against.
     * @param throwableProvider the function that provides the exception to throw.
     * @param message           the message to use in the exception.
     * @param <T>               the type to check against.
     * @param <U>               the type of the exception to throw.
     * @return the object cast to the specified type.
     * @throws U if the object is not an instance of the specified type.
     */
    public static <T, U extends Throwable> T instanceOf(Object object, @NotNull Class<T> type, @NotNull Function<String, U> throwableProvider, String message) throws U {
        if (!type.isAssignableFrom(object.getClass()))
            throw throwableProvider.apply(message);
        //noinspection unchecked
        return (T) object;
    }

    /**
     * Ensures that the given object is {@linkplain Class#isAssignableFrom(Class) an instance of} the specified type, and throws an exception if it is not. If the object is an instance of the
     * specified type, it is cast to that type and returned. The type of thrown exception can be specified.
     *
     * @param object            the object to check.
     * @param type              the class type to check against.
     * @param throwableProvider the function that provides the exception to throw.
     * @param message           the message to use in the exception.
     * @param replacements      the replacements to use in the message.
     * @param <T>               the type to check against.
     * @param <U>               the type of the exception to throw.
     * @return the object cast to the specified type.
     * @throws U if the object is not an instance of the specified type.
     */
    public static <T, U extends Throwable> T instanceOf(Object object, @NotNull Class<T> type, @NotNull BiFunction<String, Object[], U> throwableProvider, String message, Object... replacements) throws U {
        if (!type.isAssignableFrom(object.getClass()))
            throw throwableProvider.apply(message, replacements);
        //noinspection unchecked
        return (T) object;
    }

    /**
     * Ensures that the given object is {@linkplain Class#isAssignableFrom(Class) an instance of} the specified type, and throws an exception if it is not. If the object is an instance of the
     * specified type, it is cast to that type and returned.
     *
     * @param object            the object to check.
     * @param type              the class type to check against.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg               the argument to pass to the exception provider.
     * @param <T>               the type to check against.
     * @param <U>               the type of the exception to throw.
     * @param <V>               the type of the first argument to pass to the exception provider.
     * @return the object cast to the specified type.
     * @throws U if the object is not an instance of the specified type.
     */
    public static <T, U extends Throwable, V> T instanceOf(Object object, @NotNull Class<T> type, @NotNull Function<V, U> throwableProvider, V arg) throws U {
        if (!type.isAssignableFrom(object.getClass()))
            throw throwableProvider.apply(arg);
        //noinspection unchecked
        return (T) object;
    }

    /**
     * Ensures that the given object is {@linkplain Class#isAssignableFrom(Class) an instance of} the specified type, and throws an exception if it is not. If the object is an instance of the
     * specified type, it is cast to that type and returned.
     *
     * @param object            the object to check.
     * @param type              the class type to check against.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg1              the first argument to pass to the exception provider.
     * @param arg2              the second argument to pass to the exception provider.
     * @param <T>               the type to check against.
     * @param <U>               the type of the exception to throw.
     * @param <V>               the type of the first argument to pass to the exception provider.
     * @param <R>               the type of the second argument to pass to the exception provider.
     * @return the object cast to the specified type.
     * @throws U if the object is not an instance of the specified type.
     */
    public static <T, U extends Throwable, V, R> T instanceOf(Object object, @NotNull Class<T> type, @NotNull BiFunction<V, R, U> throwableProvider, V arg1, R arg2) throws U {
        if (!type.isAssignableFrom(object.getClass()))
            throw throwableProvider.apply(arg1, arg2);
        //noinspection unchecked
        return (T) object;
    }

    /**
     * Ensures that the given object is {@linkplain Class#isAssignableFrom(Class) an instance of} the specified type, and throws an exception if it is not. If the object is an instance of the
     * specified type, it is cast to that type and returned.
     *
     * @param object            the object to check.
     * @param type              the class type to check against.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg1              the first argument to pass to the exception provider.
     * @param arg2              the second argument to pass to the exception provider.
     * @param arg3              the third argument to pass to the exception provider.
     * @param <T>               the type to check against.
     * @param <U>               the type of the exception to throw.
     * @param <V>               the type of the first argument to pass to the exception provider.
     * @param <R>               the type of the second argument to pass to the exception provider.
     * @param <E>               the type of the third argument to pass to the exception provider.
     * @return the object cast to the specified type.
     * @throws U if the object is not an instance of the specified type.
     */
    public static <T, U extends Throwable, V, R, E> T instanceOf(Object object, @NotNull Class<T> type, @NotNull TriFunction<V, R, E, U> throwableProvider, V arg1, R arg2, E arg3) throws U {
        if (!type.isAssignableFrom(object.getClass()))
            throw throwableProvider.apply(arg1, arg2, arg3);
        //noinspection unchecked
        return (T) object;
    }
    //</editor-fold>

    ///////////////////////////////////////////////////////////////////////////
    //// Emptiness
    ///////////////////////////////////////////////////////////////////////////

    //<editor-fold desc="Emptiness">

    /**
     * Ensures the given object is empty. This method supports strings, {@linkplain Collection collections}, {@linkplain Map maps}, and arrays.
     *
     * @param argument the object to check.
     * @throws IllegalArgumentException if the object is not empty.
     */
    @Contract("null -> fail")
    public static void isEmpty(Object argument) {
        isNotNull(argument);
        checkEmpty(argument, IllegalArgumentException::new, null);
    }

    /**
     * Ensures the given object is empty. This method supports strings, {@linkplain Collection collections}, {@linkplain Map maps}, and arrays.
     *
     * @param argument the object to check.
     * @param message  the exception message to use if the check fails.
     * @throws IllegalArgumentException if the object is not empty.
     */
    @Contract("null, _ -> fail")
    public static void isEmpty(Object argument, String message) {
        isNotNull(argument, message);
        checkEmpty(argument, IllegalArgumentException::new, message);
    }

    /**
     * Ensures the given object is empty. This method supports strings, {@linkplain Collection collections}, {@linkplain Map maps}, and arrays.
     *
     * @param argument     the object to check.
     * @param message      the exception message to use if the check fails.
     * @param replacements the replacements to use in the exception message.
     * @throws IllegalArgumentException if the object is not empty.
     */
    @Contract("null, _, _ -> fail")
    public static void isEmpty(Object argument, @NotNull String message, Object... replacements) {
        isNotNull(argument, message, replacements);
        checkEmpty(argument, IllegalArgumentException::new, String.format(message, replacements));
    }

    /**
     * Ensures the given object is empty. This method supports strings, {@linkplain Collection collections}, {@linkplain Map maps}, and arrays.
     *
     * @param argument          the object to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param <T>               the type of the exception to throw.
     * @throws T if the object is not empty.
     */
    @Contract("null, _ -> fail")
    public static <T extends Throwable> void isEmpty(Object argument, @NotNull Supplier<T> throwableProvider) throws T {
        isNotNull(argument, throwableProvider);
        checkEmpty(argument, (s) -> throwableProvider.get(), null);
    }

    /**
     * Ensures the given object is empty. This method supports strings, {@linkplain Collection collections}, {@linkplain Map maps}, and arrays.
     *
     * @param argument          the object to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param message           the message to use in the exception.
     * @param <T>               the type of the exception to throw.
     * @throws T if the object is not empty.
     */
    @Contract("null, _, _ -> fail")
    public static <T extends Throwable> void isEmpty(Object argument, @NotNull Function<String, T> throwableProvider, String message) throws T {
        isNotNull(argument, throwableProvider, message);
        checkEmpty(argument, throwableProvider, message);
    }

    /**
     * Ensures the given object is empty. This method supports strings, {@linkplain Collection collections}, {@linkplain Map maps}, and arrays.
     *
     * @param argument          the object to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param message           the message to use in the exception.
     * @param replacements      the replacements to use in the message.
     * @param <T>               the type of the exception to throw.
     * @throws T if the object is not empty.
     */
    @Contract("null, _, _, _ -> fail")
    public static <T extends Throwable> void isEmpty(Object argument, @NotNull BiFunction<String, Object[], T> throwableProvider, String message, Object... replacements) throws T {
        isNotNull(argument, throwableProvider, message, replacements);
        checkEmpty(argument, (s) -> throwableProvider.apply(s, replacements), message);
    }

    /**
     * Ensures the given object is empty. This method supports strings, {@linkplain Collection collections}, {@linkplain Map maps}, and arrays.
     *
     * @param argument          the object to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg               the argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the argument to pass to the exception provider.
     * @throws T if the object is not empty.
     */
    @Contract("null, _, _ -> fail")
    public static <T extends Throwable, U> void isEmpty(Object argument, @NotNull Function<U, T> throwableProvider, U arg) throws T {
        isNotNull(argument, throwableProvider, arg);
        checkEmpty(argument, (s) -> throwableProvider.apply(arg), null);
    }

    /**
     * Ensures the given object is empty. This method supports strings, {@linkplain Collection collections}, {@linkplain Map maps}, and arrays.
     *
     * @param argument          the object to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg1              the first argument to pass to the exception provider.
     * @param arg2              the second argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the first argument to pass to the exception provider.
     * @param <V>               the type of the second argument to pass to the exception provider.
     * @throws T if the object is not empty.
     */
    @Contract("null, _, _, _ -> fail")
    public static <T extends Throwable, U, V> void isEmpty(Object argument, @NotNull BiFunction<U, V, T> throwableProvider, U arg1, V arg2) throws T {
        isNotNull(argument, throwableProvider, arg1, arg2);
        checkEmpty(argument, (s) -> throwableProvider.apply(arg1, arg2), null);
    }

    /**
     * Ensures the given object is empty. This method supports strings, {@linkplain Collection collections}, {@linkplain Map maps}, and arrays.
     *
     * @param argument          the object to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg1              the first argument to pass to the exception provider.
     * @param arg2              the second argument to pass to the exception provider.
     * @param arg3              the third argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the first argument to pass to the exception provider.
     * @param <V>               the type of the second argument to pass to the exception provider.
     * @param <R>               the type of the third argument to pass to the exception provider.
     * @throws T if the object is not empty.
     */
    @Contract("null, _, _, _, _ -> fail")
    public static <T extends Throwable, U, V, R> void isEmpty(Object argument, @NotNull TriFunction<U, V, R, T> throwableProvider, U arg1, V arg2, R arg3) throws T {
        isNotNull(argument, throwableProvider, arg1, arg2, arg3);
        checkEmpty(argument, (s) -> throwableProvider.apply(arg1, arg2, arg3), null);
    }

    /**
     * Ensures the given object is not empty. This method supports strings, {@linkplain Collection collections}, {@linkplain Map maps}, and arrays.
     *
     * @param argument the object to check.
     */
    @Contract("null -> fail")
    public static void isNotEmpty(Object argument) {
        isNotNull(argument);
        checkNotEmpty(argument, IllegalArgumentException::new, null);
    }

    /**
     * Ensures the given object is not empty. This method supports strings, {@linkplain Collection collections}, {@linkplain Map maps}, and arrays.
     *
     * @param argument the object to check.
     * @param message  the exception message to use if the object is empty.
     */
    @Contract("null, _ -> fail")
    public static void isNotEmpty(Object argument, String message) {
        isNotNull(argument, message);
        checkNotEmpty(argument, IllegalArgumentException::new, message);
    }

    /**
     * Ensures that a string reference passed as a parameter to the calling method is not empty, and throws an {@link IllegalArgumentException} with the specified exception message and
     * replacements if it is.
     *
     * @param argument     the string to check.
     * @param message      the exception message to use if the check fails.
     * @param replacements the replacements to use in the exception message.
     */
    @Contract("null, _, _ -> fail")
    public static void isNotEmpty(Object argument, @NotNull String message, Object... replacements) {
        isNotNull(argument, message, replacements);
        checkNotEmpty(argument, IllegalArgumentException::new, String.format(message, replacements));
    }

    /**
     * Ensures that a string reference passed as a parameter to the calling method is not empty, and throws an exception if it is.
     *
     * @param argument          the string to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param <T>               the type of the exception to throw.
     * @throws T if the object is empty.
     */
    @Contract("null, _ -> fail")
    public static <T extends Throwable> void isNotEmpty(Object argument, @NotNull Supplier<T> throwableProvider) throws T {
        isNotNull(argument, throwableProvider);
        checkNotEmpty(argument, (s) -> throwableProvider.get(), null);
    }

    /**
     * Ensures that a string reference passed as a parameter to the calling method is not empty, and throws an exception if it is.
     *
     * @param argument          the string to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param message           the message to use in the exception.
     * @param <T>               the type of the exception to throw.
     * @throws T if the object is empty.
     */
    @Contract("null, _, _ -> fail")
    public static <T extends Throwable> void isNotEmpty(Object argument, @NotNull Function<String, T> throwableProvider, String message) throws T {
        isNotNull(argument, throwableProvider, message);
        checkNotEmpty(argument, throwableProvider, message);
    }

    /**
     * Ensures that a string reference passed as a parameter to the calling method is not empty, and throws an exception if it is. The type of thrown exception can be specified.
     *
     * @param argument          the string to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param message           the message to use in the exception.
     * @param replacements      the replacements to use in the message.
     * @param <T>               the type of the exception to throw.
     * @throws T if the object is empty.
     */
    @Contract("null, _, _, _ -> fail")
    public static <T extends Throwable> void isNotEmpty(Object argument, @NotNull BiFunction<String, Object[], T> throwableProvider, String message, Object... replacements) throws T {
        isNotNull(argument, throwableProvider, message, replacements);
        checkNotEmpty(argument, (s) -> throwableProvider.apply(s, replacements), message);
    }

    /**
     * Ensures that a string reference passed as a parameter to the calling method is not empty, and throws an exception if it is.
     *
     * @param argument          the string to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg               the argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the argument to pass to the exception provider.
     * @throws T if the object is empty.
     */
    @Contract("null, _, _ -> fail")
    public static <T extends Throwable, U> void isNotEmpty(Object argument, @NotNull Function<U, T> throwableProvider, U arg) throws T {
        isNotNull(argument, throwableProvider, arg);
        checkNotEmpty(argument, (s) -> throwableProvider.apply(arg), null);
    }

    /**
     * Ensures that a string reference passed as a parameter to the calling method is not empty, and throws an exception if it is.
     *
     * @param argument          the string to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg1              the first argument to pass to the exception provider.
     * @param arg2              the second argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the first argument to pass to the exception provider.
     * @param <V>               the type of the second argument to pass to the exception provider.
     * @throws T if the object is empty.
     */
    @Contract("null, _, _, _ -> fail")
    public static <T extends Throwable, U, V> void isNotEmpty(Object argument, @NotNull BiFunction<U, V, T> throwableProvider, U arg1, V arg2) throws T {
        isNotNull(argument, throwableProvider, arg1, arg2);
        checkNotEmpty(argument, (s) -> throwableProvider.apply(arg1, arg2), null);
    }

    /**
     * Ensures that a string reference passed as a parameter to the calling method is not empty, and throws an exception if it is.
     *
     * @param argument          the string to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg1              the first argument to pass to the exception provider.
     * @param arg2              the second argument to pass to the exception provider.
     * @param arg3              the third argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the first argument to pass to the exception provider.
     * @param <V>               the type of the second argument to pass to the exception provider.
     * @param <R>               the type of the third argument to pass to the exception provider.
     * @throws T if the object is empty.
     */
    @Contract("null, _, _, _, _ -> fail")
    public static <T extends Throwable, U, V, R> void isNotEmpty(Object argument, @NotNull TriFunction<U, V, R, T> throwableProvider, U arg1, V arg2, R arg3) throws T {
        isNotNull(argument, throwableProvider, arg1, arg2, arg3);
        checkNotEmpty(argument, (s) -> throwableProvider.apply(arg1, arg2, arg3), null);
    }
    //</editor-fold>

    ///////////////////////////////////////////////////////////////////////////
    //// Blankness
    ///////////////////////////////////////////////////////////////////////////

    //<editor-fold desc="Blankness">

    /**
     * Ensures that the given string is blank.
     *
     * @param argument the string to check.
     * @throws IllegalArgumentException if the string is not blank.
     */
    @Contract("null -> fail")
    public static void isBlank(String argument) {
        isNotNull(argument);
        if (!argument.isBlank())
            throw new IllegalArgumentException();
    }

    /**
     * Ensures that the given string is blank.
     *
     * @param argument the string to check.
     * @param message  the exception message to use if the check fails.
     * @throws IllegalArgumentException if the string is not blank.
     */
    @Contract("null, _ -> fail")
    public static void isBlank(String argument, String message) {
        isNotNull(argument, message);
        if (!argument.isBlank())
            throw new IllegalArgumentException(message);
    }

    /**
     * Ensures that the given string is blank.
     *
     * @param argument     the string to check.
     * @param message      the exception message to use if the check fails.
     * @param replacements the replacements to use in the exception message.
     * @throws IllegalArgumentException if the string is not blank.
     */
    @Contract("null, _, _ -> fail")
    public static void isBlank(String argument, @NotNull String message, Object... replacements) {
        isNotNull(argument, message, replacements);
        if (!argument.isBlank())
            throw new IllegalArgumentException(String.format(message, replacements));
    }

    /**
     * Ensures that the given string is blank.
     *
     * @param argument          the string to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param <T>               the type of the exception to throw.
     * @throws T if the string is not blank.
     */
    @Contract("null, _ -> fail")
    public static <T extends Throwable> void isBlank(String argument, @NotNull Supplier<T> throwableProvider) throws T {
        isNotNull(argument, throwableProvider);
        if (!argument.isBlank())
            throw throwableProvider.get();
    }

    /**
     * Ensures that the given string is blank.
     *
     * @param argument          the string to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param message           the message to use in the exception.
     * @param <T>               the type of the exception to throw.
     * @throws T if the string is not blank.
     */
    @Contract("null, _, _ -> fail")
    public static <T extends Throwable> void isBlank(String argument, @NotNull Function<String, T> throwableProvider, String message) throws T {
        isNotNull(argument, throwableProvider, message);
        if (!argument.isBlank())
            throw throwableProvider.apply(message);
    }

    /**
     * Ensures that the given string is blank.
     *
     * @param argument          the string to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param message           the message to use in the exception.
     * @param replacements      the replacements to use in the message.
     * @param <T>               the type of the exception to throw.
     * @throws T if the string is not blank.
     */
    @Contract("null, _, _, _ -> fail")
    public static <T extends Throwable> void isBlank(String argument, @NotNull BiFunction<String, Object[], T> throwableProvider, String message, Object... replacements) throws T {
        isNotNull(argument, throwableProvider, message, replacements);
        if (!argument.isBlank())
            throw throwableProvider.apply(message, replacements);
    }

    /**
     * Ensures that the given string is blank.
     *
     * @param argument          the string to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg               the argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the argument to pass to the exception provider.
     * @throws T if the string is not blank.
     */
    @Contract("null, _, _ -> fail")
    public static <T extends Throwable, U> void isBlank(String argument, @NotNull Function<U, T> throwableProvider, U arg) throws T {
        isNotNull(argument, throwableProvider, arg);
        if (!argument.isBlank())
            throw throwableProvider.apply(arg);
    }

    /**
     * Ensures that the given string is blank.
     *
     * @param argument          the string to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg1              the first argument to pass to the exception provider.
     * @param arg2              the second argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the first argument to pass to the exception provider.
     * @param <V>               the type of the second argument to pass to the exception provider.
     * @throws T if the string is not blank.
     */
    @Contract("null, _, _, _ -> fail")
    public static <T extends Throwable, U, V> void isBlank(String argument, @NotNull BiFunction<U, V, T> throwableProvider, U arg1, V arg2) throws T {
        isNotNull(argument, throwableProvider, arg1, arg2);
        if (!argument.isBlank())
            throw throwableProvider.apply(arg1, arg2);
    }

    /**
     * Ensures that the given string is blank.
     *
     * @param argument          the string to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg1              the first argument to pass to the exception provider.
     * @param arg2              the second argument to pass to the exception provider.
     * @param arg3              the third argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the first argument to pass to the exception provider.
     * @param <V>               the type of the second argument to pass to the exception provider.
     * @param <R>               the type of the third argument to pass to the exception provider.
     * @throws T if the string is not blank.
     */
    @Contract("null, _, _, _, _ -> fail")
    public static <T extends Throwable, U, V, R> void isBlank(String argument, @NotNull TriFunction<U, V, R, T> throwableProvider, U arg1, V arg2, R arg3) throws T {
        isNotNull(argument, throwableProvider, arg1, arg2, arg3);
        if (!argument.isBlank())
            throw throwableProvider.apply(arg1, arg2, arg3);
    }

    /**
     * Ensures that the given string is not blank.
     *
     * @param argument the string to check.
     * @throws IllegalArgumentException if the string is blank or {@code null}.
     */
    @Contract("null -> fail")
    public static void isNotBlank(String argument) {
        isNotNull(argument);
        if (argument.isBlank())
            throw new IllegalArgumentException();
    }

    /**
     * Ensures that the given string is not blank.
     *
     * @param argument the string to check.
     * @param message  the exception message to use if the string is blank.
     * @throws IllegalArgumentException if the string is blank or {@code null}.
     */
    @Contract("null, _ -> fail")
    public static void isNotBlank(String argument, String message) {
        isNotNull(argument, message);
        if (argument.isBlank())
            throw new IllegalArgumentException(message);
    }

    /**
     * Ensures that the given string is not blank.
     *
     * @param argument     the string to check.
     * @param message      the exception message to use if the string is blank.
     * @param replacements the replacements to use in the exception message.
     * @throws IllegalArgumentException if the string is blank or {@code null}.
     */
    @Contract("null, _, _ -> fail")
    public static void isNotBlank(String argument, @NotNull String message, Object... replacements) {
        isNotNull(argument, message, replacements);
        if (argument.isBlank())
            throw new IllegalArgumentException(String.format(message, replacements));
    }

    /**
     * Ensures that the given string is not blank.
     *
     * @param argument          the string to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param <T>               the type of the exception to throw.
     * @throws T if the string is blank or {@code null}.
     */
    @Contract("null, _ -> fail")
    public static <T extends Throwable> void isNotBlank(String argument, @NotNull Supplier<T> throwableProvider) throws T {
        isNotNull(argument, throwableProvider);
        if (argument.isBlank())
            throw throwableProvider.get();
    }

    /**
     * Ensures that the given string is not blank.
     *
     * @param argument          the string to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param message           the message to use in the exception.
     * @param <T>               the type of the exception to throw.
     * @throws T if the string is blank or {@code null}.
     */
    @Contract("null, _, _ -> fail")
    public static <T extends Throwable> void isNotBlank(String argument, @NotNull Function<String, T> throwableProvider, String message) throws T {
        isNotNull(argument, throwableProvider, message);
        if (argument.isBlank())
            throw throwableProvider.apply(message);
    }

    /**
     * Ensures that the given string is not blank.
     *
     * @param argument          the string to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param message           the message to use in the exception.
     * @param replacements      the replacements to use in the message.
     * @param <T>               the type of the exception to throw.
     * @throws T if the string is blank or {@code null}.
     */
    @Contract("null, _, _, _ -> fail")
    public static <T extends Throwable> void isNotBlank(String argument, @NotNull BiFunction<String, Object[], T> throwableProvider, String message, Object... replacements) throws T {
        isNotNull(argument, throwableProvider, message, replacements);
        if (argument.isBlank())
            throw throwableProvider.apply(message, replacements);
    }

    /**
     * Ensures that the given string is not blank.
     *
     * @param argument          the string to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg               the argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the argument to pass to the exception provider.
     * @throws T if the string is blank or {@code null}.
     */
    @Contract("null, _, _ -> fail")
    public static <T extends Throwable, U> void isNotBlank(String argument, @NotNull Function<U, T> throwableProvider, U arg) throws T {
        isNotNull(argument, throwableProvider, arg);
        if (argument.isBlank())
            throw throwableProvider.apply(arg);
    }

    /**
     * Ensures that the given string is not blank.
     *
     * @param argument          the string to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg1              the first argument to pass to the exception provider.
     * @param arg2              the second argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the first argument to pass to the exception provider.
     * @param <V>               the type of the second argument to pass to the exception provider.
     * @throws T if the string is blank or {@code null}.
     */
    @Contract("null, _, _, _ -> fail")
    public static <T extends Throwable, U, V> void isNotBlank(String argument, @NotNull BiFunction<U, V, T> throwableProvider, U arg1, V arg2) throws T {
        isNotNull(argument, throwableProvider, arg1, arg2);
        if (argument.isBlank())
            throw throwableProvider.apply(arg1, arg2);
    }

    /**
     * Ensures that the given string is not blank.
     *
     * @param argument          the string to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg1              the first argument to pass to the exception provider.
     * @param arg2              the second argument to pass to the exception provider.
     * @param arg3              the third argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the first argument to pass to the exception provider.
     * @param <V>               the type of the second argument to pass to the exception provider.
     * @param <R>               the type of the third argument to pass to the exception provider.
     * @throws T if the string is blank or {@code null}.
     */
    @Contract("null, _, _, _, _ -> fail")
    public static <T extends Throwable, U, V, R> void isNotBlank(String argument, @NotNull TriFunction<U, V, R, T> throwableProvider, U arg1, V arg2, R arg3) throws T {
        isNotNull(argument, throwableProvider, arg1, arg2, arg3);
        if (argument.isBlank())
            throw throwableProvider.apply(arg1, arg2, arg3);
    }

    //</editor-fold>

    ///////////////////////////////////////////////////////////////////////////
    //// Numbers
    ///////////////////////////////////////////////////////////////////////////

    //<editor-fold desc="Numbers">

    /**
     * Ensures that the given number is positive.
     *
     * @param argument the number to check.
     */
    public static void isPositive(@NotNull Number argument) {
        if (argument.doubleValue() <= 0)
            throw new IllegalArgumentException();
    }

    /**
     * Ensures that the given number is positive.
     *
     * @param argument the number to check.
     * @param message  the exception message to use if the check fails.
     */
    public static void isPositive(@NotNull Number argument, String message) {
        if (argument.doubleValue() <= 0)
            throw new IllegalArgumentException(message);
    }

    /**
     * Ensures that the given number is positive.
     *
     * @param argument     the number to check.
     * @param message      the exception message to use if the check fails.
     * @param replacements the replacements to use in the exception message.
     */
    public static void isPositive(@NotNull Number argument, @NotNull String message, Object... replacements) {
        if (argument.doubleValue() <= 0)
            throw new IllegalArgumentException(String.format(message, replacements));
    }

    /**
     * Ensures that the given number is positive.
     *
     * @param argument          the number to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param <T>               the type of the exception to throw.
     * @throws T if the number is not positive.
     */
    public static <T extends Throwable> void isPositive(@NotNull Number argument, @NotNull Supplier<T> throwableProvider) throws T {
        if (argument.doubleValue() <= 0)
            throw throwableProvider.get();
    }

    /**
     * Ensures that the given number is positive.
     *
     * @param argument          the number to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param message           the message to use in the exception.
     * @param <T>               the type of the exception to throw.
     * @throws T if the number is not positive.
     */
    public static <T extends Throwable> void isPositive(@NotNull Number argument, @NotNull Function<String, T> throwableProvider, String message) throws T {
        if (argument.doubleValue() <= 0)
            throw throwableProvider.apply(message);
    }

    /**
     * Ensures that the given number is positive.
     *
     * @param argument          the number to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param message           the message to use in the exception.
     * @param replacements      the replacements to use in the message.
     * @param <T>               the type of the exception to throw.
     * @throws T if the number is not positive.
     */
    public static <T extends Throwable> void isPositive(@NotNull Number argument, @NotNull BiFunction<String, Object[], T> throwableProvider, String message, Object... replacements) throws T {
        if (argument.doubleValue() <= 0)
            throw throwableProvider.apply(message, replacements);
    }

    /**
     * Ensures that the given number is positive.
     *
     * @param argument          the number to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg               the argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the argument to pass to the exception provider.
     * @throws T if the number is not positive.
     */
    public static <T extends Throwable, U> void isPositive(@NotNull Number argument, @NotNull Function<U, T> throwableProvider, U arg) throws T {
        if (argument.doubleValue() <= 0)
            throw throwableProvider.apply(arg);
    }

    /**
     * Ensures that the given number is positive.
     *
     * @param argument          the number to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg1              the first argument to pass to the exception provider.
     * @param arg2              the second argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the first argument to pass to the exception provider.
     * @param <V>               the type of the second argument to pass to the exception provider.
     * @throws T if the number is not positive.
     */
    public static <T extends Throwable, U, V> void isPositive(@NotNull Number argument, @NotNull BiFunction<U, V, T> throwableProvider, U arg1, V arg2) throws T {
        if (argument.doubleValue() <= 0)
            throw throwableProvider.apply(arg1, arg2);
    }

    /**
     * Ensures that the given number is positive.
     *
     * @param argument          the number to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg1              the first argument to pass to the exception provider.
     * @param arg2              the second argument to pass to the exception provider.
     * @param arg3              the third argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the first argument to pass to the exception provider.
     * @param <V>               the type of the second argument to pass to the exception provider.
     * @param <R>               the type of the third argument to pass to the exception provider.
     * @throws T if the number is not positive.
     */
    public static <T extends Throwable, U, V, R> void isPositive(@NotNull Number argument, @NotNull TriFunction<U, V, R, T> throwableProvider, U arg1, V arg2, R arg3) throws T {
        if (argument.doubleValue() <= 0)
            throw throwableProvider.apply(arg1, arg2, arg3);
    }

    /**
     * Ensures that the given number is negative.
     *
     * @param argument the number to check.
     */
    public static void isNegative(@NotNull Number argument) {
        if (argument.doubleValue() >= 0)
            throw new IllegalArgumentException();
    }

    /**
     * Ensures that the given number is negative.
     *
     * @param argument the number to check.
     * @param message  the exception message to use if the check fails.
     */
    public static void isNegative(@NotNull Number argument, String message) {
        if (argument.doubleValue() >= 0)
            throw new IllegalArgumentException(message);
    }

    /**
     * Ensures that the given number is negative.
     *
     * @param argument     the number to check.
     * @param message      the exception message to use if the check fails.
     * @param replacements the replacements to use in the exception message.
     */
    public static void isNegative(@NotNull Number argument, @NotNull String message, Object... replacements) {
        if (argument.doubleValue() >= 0)
            throw new IllegalArgumentException(String.format(message, replacements));
    }

    /**
     * Ensures that the given number is negative.
     *
     * @param argument          the number to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param <T>               the type of the exception to throw.
     * @throws T if the number is not negative.
     */
    public static <T extends Throwable> void isNegative(@NotNull Number argument, @NotNull Supplier<T> throwableProvider) throws T {
        if (argument.doubleValue() >= 0)
            throw throwableProvider.get();
    }

    /**
     * Ensures that the given number is negative.
     *
     * @param argument          the number to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param message           the message to use in the exception.
     * @param <T>               the type of the exception to throw.
     * @throws T if the number is not negative.
     */
    public static <T extends Throwable> void isNegative(@NotNull Number argument, @NotNull Function<String, T> throwableProvider, String message) throws T {
        if (argument.doubleValue() >= 0)
            throw throwableProvider.apply(message);
    }

    /**
     * Ensures that the given number is negative.
     *
     * @param argument          the number to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param message           the message to use in the exception.
     * @param replacements      the replacements to use in the message.
     * @param <T>               the type of the exception to throw.
     * @throws T if the number is not negative.
     */
    public static <T extends Throwable> void isNegative(@NotNull Number argument, @NotNull BiFunction<String, Object[], T> throwableProvider, String message, Object... replacements) throws T {
        if (argument.doubleValue() >= 0)
            throw throwableProvider.apply(message, replacements);
    }

    /**
     * Ensures that the given number is negative.
     *
     * @param argument          the number to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg               the argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the argument to pass to the exception provider.
     * @throws T if the number is not negative.
     */
    public static <T extends Throwable, U> void isNegative(@NotNull Number argument, @NotNull Function<U, T> throwableProvider, U arg) throws T {
        if (argument.doubleValue() >= 0)
            throw throwableProvider.apply(arg);
    }

    /**
     * Ensures that the given number is negative.
     *
     * @param argument          the number to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg1              the first argument to pass to the exception provider.
     * @param arg2              the second argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the first argument to pass to the exception provider.
     * @param <V>               the type of the second argument to pass to the exception provider.
     * @throws T if the number is not negative.
     */
    public static <T extends Throwable, U, V> void isNegative(@NotNull Number argument, @NotNull BiFunction<U, V, T> throwableProvider, U arg1, V arg2) throws T {
        if (argument.doubleValue() >= 0)
            throw throwableProvider.apply(arg1, arg2);
    }

    /**
     * Ensures that the given number is negative.
     *
     * @param argument          the number to check.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg1              the first argument to pass to the exception provider.
     * @param arg2              the second argument to pass to the exception provider.
     * @param arg3              the third argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the first argument to pass to the exception provider.
     * @param <V>               the type of the second argument to pass to the exception provider.
     * @param <R>               the type of the third argument to pass to the exception provider.
     * @throws T if the number is not negative.
     */
    public static <T extends Throwable, U, V, R> void isNegative(@NotNull Number argument, @NotNull TriFunction<U, V, R, T> throwableProvider, U arg1, V arg2, R arg3) throws T {
        if (argument.doubleValue() >= 0)
            throw throwableProvider.apply(arg1, arg2, arg3);
    }

    /**
     * Ensures that the given number is between the specified minimum and maximum values.
     *
     * @param argument the number to check.
     * @param min      the minimum value the number can be.
     * @param max      the maximum value the number can be.
     */
    public static void isBetween(@NotNull Number argument, @NotNull Number min, @NotNull Number max) {
        if (argument.doubleValue() < min.doubleValue() || argument.doubleValue() > max.doubleValue())
            throw new IllegalArgumentException();
    }

    /**
     * Ensures that the given number is between the specified minimum and maximum values.
     *
     * @param argument the number to check.
     * @param min      the minimum value the number can be.
     * @param max      the maximum value the number can be.
     * @param message  the exception message to use if the check fails.
     */
    public static void isBetween(@NotNull Number argument, @NotNull Number min, @NotNull Number max, String message) {
        if (argument.doubleValue() < min.doubleValue() || argument.doubleValue() > max.doubleValue())
            throw new IllegalArgumentException(message);
    }

    /**
     * Ensures that the given number is between the specified minimum and maximum values.
     *
     * @param argument     the number to check.
     * @param min          the minimum value the number can be.
     * @param max          the maximum value the number can be.
     * @param message      the exception message to use if the check fails.
     * @param replacements the replacements to use in the exception message.
     */
    public static void isBetween(@NotNull Number argument, @NotNull Number min, @NotNull Number max, @NotNull String message, Object... replacements) {
        if (argument.doubleValue() < min.doubleValue() || argument.doubleValue() > max.doubleValue())
            throw new IllegalArgumentException(String.format(message, replacements));
    }

    /**
     * Ensures that the given number is between the specified minimum and maximum values.
     *
     * @param argument          the number to check.
     * @param min               the minimum value the number can be.
     * @param max               the maximum value the number can be.
     * @param throwableProvider the function that provides the exception to throw.
     * @param <T>               the type of the exception to throw.
     * @throws T if the number is not between the specified values.
     */
    public static <T extends Throwable> void isBetween(@NotNull Number argument, @NotNull Number min, @NotNull Number max, @NotNull Supplier<T> throwableProvider) throws T {
        if (argument.doubleValue() < min.doubleValue() || argument.doubleValue() > max.doubleValue())
            throw throwableProvider.get();
    }

    /**
     * Ensures that the given number is between the specified minimum and maximum values.
     *
     * @param argument          the number to check.
     * @param min               the minimum value the number can be.
     * @param max               the maximum value the number can be.
     * @param throwableProvider the function that provides the exception to throw.
     * @param message           the message to use in the exception.
     * @param <T>               the type of the exception to throw.
     * @throws T if the number is not between the specified values.
     */
    public static <T extends Throwable> void isBetween(@NotNull Number argument, @NotNull Number min, @NotNull Number max, @NotNull Function<String, T> throwableProvider, String message) throws T {
        if (argument.doubleValue() < min.doubleValue() || argument.doubleValue() > max.doubleValue())
            throw throwableProvider.apply(message);
    }

    /**
     * Ensures that the given number is between the specified minimum and maximum values.
     *
     * @param argument          the number to check.
     * @param min               the minimum value the number can be.
     * @param max               the maximum value the number can be.
     * @param throwableProvider the function that provides the exception to throw.
     * @param message           the message to use in the exception.
     * @param replacements      the replacements to use in the message.
     * @param <T>               the type of the exception to throw.
     * @throws T if the number is not between the specified values.
     */
    public static <T extends Throwable> void isBetween(@NotNull Number argument, @NotNull Number min, @NotNull Number max, @NotNull BiFunction<String, Object[], T> throwableProvider, String message, Object... replacements) throws T {
        if (argument.doubleValue() < min.doubleValue() || argument.doubleValue() > max.doubleValue())
            throw throwableProvider.apply(message, replacements);
    }

    /**
     * Ensures that the given number is between the specified minimum and maximum values.
     *
     * @param argument          the number to check.
     * @param min               the minimum value the number can be.
     * @param max               the maximum value the number can be.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg               the argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the argument to pass to the exception provider.
     * @throws T if the number is not between the specified values.
     */
    public static <T extends Throwable, U> void isBetween(@NotNull Number argument, @NotNull Number min, @NotNull Number max, @NotNull Function<U, T> throwableProvider, U arg) throws T {
        if (argument.doubleValue() < min.doubleValue() || argument.doubleValue() > max.doubleValue())
            throw throwableProvider.apply(arg);
    }

    /**
     * Ensures that the given number is between the specified minimum and maximum values.
     *
     * @param argument          the number to check.
     * @param min               the minimum value the number can be.
     * @param max               the maximum value the number can be.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg1              the first argument to pass to the exception provider.
     * @param arg2              the second argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the first argument to pass to the exception provider.
     * @param <V>               the type of the second argument to pass to the exception provider.
     * @throws T if the number is not between the specified values.
     */
    public static <T extends Throwable, U, V> void isBetween(@NotNull Number argument, @NotNull Number min, @NotNull Number max, @NotNull BiFunction<U, V, T> throwableProvider, U arg1, V arg2) throws T {
        if (argument.doubleValue() < min.doubleValue() || argument.doubleValue() > max.doubleValue())
            throw throwableProvider.apply(arg1, arg2);
    }

    /**
     * Ensures that the given number is between the specified minimum and maximum values.
     *
     * @param argument          the number to check.
     * @param min               the minimum value the number can be.
     * @param max               the maximum value the number can be.
     * @param throwableProvider the function that provides the exception to throw.
     * @param arg1              the first argument to pass to the exception provider.
     * @param arg2              the second argument to pass to the exception provider.
     * @param arg3              the third argument to pass to the exception provider.
     * @param <T>               the type of the exception to throw.
     * @param <U>               the type of the first argument to pass to the exception provider.
     * @param <V>               the type of the second argument to pass to the exception provider.
     * @param <R>               the type of the third argument to pass to the exception provider.
     * @throws T if the number is not between the specified values.
     */
    public static <T extends Throwable, U, V, R> void isBetween(@NotNull Number argument, @NotNull Number min, @NotNull Number max, @NotNull TriFunction<U, V, R, T> throwableProvider, U arg1, V arg2, R arg3) throws T {
        if (argument.doubleValue() < min.doubleValue() || argument.doubleValue() > max.doubleValue())
            throw throwableProvider.apply(arg1, arg2, arg3);
    }
    //</editor-fold>

    // --- Internal --- //

    // Checks if the given string, collection, map, or array is empty, and throws a custom exception if it is
    private static <T extends Throwable> void checkEmpty(Object argument, Function<String, T> throwableFunction, String message) throws T {
        if (argument.getClass().isArray() && Array.getLength(argument) != 0)
            throw throwableFunction.apply(message);

        if (argument instanceof String)
            check(((String) argument).isEmpty(), throwableFunction, message);
        else if (argument instanceof Collection<?>)
            check(((Collection<?>) argument).isEmpty(), throwableFunction, message);
        else if (argument instanceof Map<?, ?>)
            check(((Map<?, ?>) argument).isEmpty(), throwableFunction, message);
        else
            throw throwableFunction.apply(message);
    }

    // Checks if the given string, collection, map, or array is not empty, and throws a custom exception if it is
    private static <T extends Throwable> void checkNotEmpty(Object argument, Function<String, T> throwableFunction, String message) throws T {
        if (argument.getClass().isArray() && Array.getLength(argument) == 0)
            throw throwableFunction.apply(message);

        if (argument instanceof String)
            check(!((String) argument).isEmpty(), throwableFunction, message);
        else if (argument instanceof Collection<?>)
            check(!((Collection<?>) argument).isEmpty(), throwableFunction, message);
        else if (argument instanceof Map<?, ?>)
            check(!((Map<?, ?>) argument).isEmpty(), throwableFunction, message);
        else
            throw throwableFunction.apply(message);
    }
}
