/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package be.hyperrail.android.irail.implementation;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import be.hyperrail.android.irail.db.Station;

/**
 * Train information, except its stops.
 * This data is typically present in the API without requiring a second API call.
 */
public class TrainStub implements Serializable {

    private final String uri;

    @NonNull
    protected String id;
    protected Station direction;

    // Direction is required, since we need to display something
    public TrainStub(@NonNull String id, @NonNull Station direction, String uri) {
        // TODO: null IDs should be disallowed once the old suggestion code has been removed
        if (id != null) {
            // TODO: all ids should have a correct prefix already, should not be tightly coupled to iRail
            if (!id.startsWith("BE.NMBS.")) {
                id = "BE.NMBS." + id;
            }

            this.id = id.toUpperCase();
        }
        this.direction = direction;
        this.uri = uri;
    }

    /**
     * The ID, for example BE.NMBS.IC4516
     *
     * @return ID, for example BE.NMBS.IC4516
     */
    @NonNull
    public String getId() {
        return id;
    }

    /**
     * The direction (final stop) of this train
     *
     * @return direction (final stop) of this train
     */
    public Station getDirection() {
        return direction;
    }

    /**
     * Human-readable name, for example IC 4516
     *
     * @return Human-readable name
     */
    public String getName() {
        return getType() + " " + getNumber();
    }

    /**
     * ID without leading BE.NMBS, for example IC4516
     *
     * @return ID without leading BE.NMBS
     */
    private String getReducedId() {
        return this.id.substring(8);
    }

    /**
     * Semantic ID, for example http://irail.be/vehicle/IC4516
     *
     * @return Semantic ID
     */
    public String getSemanticId() {
        if (uri != null) {
            return uri;
        }
        // Calculate if unknown
        return "http://irail.be/vehicle/" + getReducedId();
    }

    /**
     * Train type, for example S, IC, L, P
     *
     * @return The type of this train
     */
    public String getType() {
        String rid = getReducedId();
        // S trains are special
        if (rid.startsWith("S")) {
            return rid.substring(0, rid.length() - 4);
        }

        String pattern = "(\\w+?)(\\d+)";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(rid);
        if (m.find()) {
            return m.group(1);
        }
        return "";
    }

    /**
     * Train number, for example 4516
     *
     * @return The number of this train
     */
    public String getNumber() {
        String rid = getReducedId();
        // S trains are special
        if (rid.startsWith("S")) {
            return rid.substring(rid.length() - 4);
        }

        String pattern = "(\\w+?)(\\d+)";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(rid);
        if (m.find()) {
            return m.group(2);
        }
        return "";
    }
}
