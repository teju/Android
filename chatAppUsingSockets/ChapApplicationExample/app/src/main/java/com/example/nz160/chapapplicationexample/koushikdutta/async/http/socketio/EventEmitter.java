package com.example.nz160.chapapplicationexample.koushikdutta.async.http.socketio;

import com.koushikdutta.async.http.socketio.*;
import com.koushikdutta.async.util.HashList;

import org.json.JSONArray;

import java.util.Iterator;
import java.util.List;

/**
 * Created by koush on 7/1/13.
 */
public class EventEmitter {
    interface OnceCallback extends EventCallback {
    }

    HashList<EventCallback> callbacks = new HashList<EventCallback>();
    
    void onEvent(String event, JSONArray arguments, com.koushikdutta.async.http.socketio.Acknowledge acknowledge) {
        List<EventCallback> list = callbacks.get(event);
        if (list == null)
            return;
        Iterator<EventCallback> iter = list.iterator();
        while (iter.hasNext()) {
            EventCallback cb = iter.next();
            cb.onEvent(event, arguments, acknowledge);
            if (cb instanceof OnceCallback)
                iter.remove();
        }
    }

    public void addListener(String event, EventCallback callback) {
        on(event, callback);
    }

    public void once(final String event, final EventCallback callback) {
        on(event, new OnceCallback() {
            @Override
            public void onEvent(String event, JSONArray arguments, com.koushikdutta.async.http.socketio.Acknowledge acknowledge) {
                callback.onEvent(event, arguments, acknowledge);
            }
        });
    }

    public void on(String event, EventCallback callback) {
        callbacks.add(event, callback);
    }

    public void removeListener(String event, EventCallback callback) {
        List<EventCallback> list = callbacks.get(event);
        if (list == null)
            return;
        list.remove(callback);
    }
}
