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

package be.hyperrail.android.fragments.searchresult;

import android.support.annotation.NonNull;

import be.hyperrail.android.irail.contracts.IrailRequest;
import be.hyperrail.android.util.OnDateTimeSetListener;

public interface ResultFragment<T extends IrailRequest> extends OnDateTimeSetListener {

    /**
     * Update the Request object for which this fragment shows data, and retrieve new data.
     * @param request The new Request object for this fragment
     */
    void setRequest(@NonNull T request);

    /**
     * Get the current Request object for which results are shown
     * @return The current request object
     */
    T getRequest();
}
