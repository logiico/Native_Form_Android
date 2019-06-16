package com.example.nativeformmakerandroid.observe;

import android.location.Location;

import java.util.Observable;

/**
 * Created by mbr on 2/22/17.
 */

/**
 * JavaDocs ,Created by tgs on 09/02/17.
 * This Observable (subject) is created to inform it's observers
 * { simple Observer class which is implemented } if new location is received by GPS!
 * <p>
 * The classes which implements this observer will be informed if new location is received
 * and changes the mylocation on the map .
 * <p>
 * <p>
 * Note : The Model by which the change is send to classes implemented observer is
 * {@link Location}
 * <p>
 * Current Registered Observable or Subject are :
 * <p>
 * {@link com.example.nativeformmakerandroid.service.LogService}
 * in this service class - which is responsible for receiving and saving in DB and sending
 * to server user locations - the location is received from GPS and immediately informs the
 * observer class .
 * <p>
 * <p>
 * Current Registered Observers are :
 * <p>
 * {@link com.example.nativeformmakerandroid.fragment.MapsFragmentDetails}
 *
 * @See https://www.javacodegeeks.com/2013/08/observer-design-pattern-in-java-example-tutorial.html
 */
public class GPSObservable extends Observable {
    private static GPSObservable ourInstance = new GPSObservable();

    private GPSObservable() {
    }

    public static GPSObservable getInstance() {
        return ourInstance;
    }

    public void locationChanged(Location location) {
        setChanged();
        notifyObservers(location);
    }
}
