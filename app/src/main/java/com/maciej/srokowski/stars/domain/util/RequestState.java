package com.maciej.srokowski.stars.domain.util;

import android.support.annotation.Nullable;

import static com.maciej.srokowski.stars.domain.util.Status.ERROR;
import static com.maciej.srokowski.stars.domain.util.Status.SUCCESS;

/**
 * This could be sealed class in Kotlin but we don't have algebraic data types in Java :/
 *
 * @param <T> Excellent type
 */
public class RequestState<T> {

    public final Status status;

    @Nullable
    public final T data;

    @Nullable
    public final Throwable error;

    public static <T> RequestState<T> success(T data) {
        return new RequestState<>(SUCCESS, data, null);
    }

    public static <T> RequestState<T> error(Throwable error) {
        return new RequestState<>(ERROR, null, error);
    }

    private RequestState(Status status, @Nullable T data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    @Override
    public String toString() {
        return "RequestState{" +
                "status=" + status +
                ", data=" + data +
                ", error=" + error +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestState<?> that = (RequestState<?>) o;

        if (status != that.status) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        return error != null ? error.equals(that.error) : that.error == null;
    }

    @Override
    public int hashCode() {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (error != null ? error.hashCode() : 0);
        return result;
    }
}