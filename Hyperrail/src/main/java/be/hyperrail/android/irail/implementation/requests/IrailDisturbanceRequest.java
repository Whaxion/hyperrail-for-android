/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package be.hyperrail.android.irail.implementation.requests;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import be.hyperrail.android.irail.contracts.IrailRequest;
import be.hyperrail.android.irail.implementation.Disturbance;


/**
 * A request for disturbance information
 */
public class IrailDisturbanceRequest extends IrailBaseRequest<Disturbance[]> implements IrailRequest<Disturbance[]> {

    public IrailDisturbanceRequest(){
    }

    public IrailDisturbanceRequest(@NonNull JSONObject json) throws JSONException {
        super(json);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof IrailDisturbanceRequest);
    }

    @Override
    public int compareTo(@NonNull IrailRequest o) {
        if (! (o instanceof  IrailDisturbanceRequest)){
            return -1;
        }

        return 0;
    }

    @Override
    public boolean equalsIgnoringTime(IrailRequest other) {
        return other instanceof IrailDisturbanceRequest;
    }
}
