package eu.urbancoders.zonkysniper.events;

import eu.urbancoders.zonkysniper.core.Constants;

/**
 * Slouzi pro odregistraci aplikace treti strany, tim padem znemozneni prijimani notifikaci, napr. od RoboZonky
 *
 * Author: Ondrej Steger (ondrej@steger.cz)
 * Date: 25.12.2016
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 3.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

public class UnregisterThirdpartyNotif {

    public static class Request {
        String username;
        Constants.ClientApps clientApp;

        public Request(String username, Constants.ClientApps clientApp) {
            this.username = username;
            this.clientApp = clientApp;
        }

        public String getUsername() {
            return username;
        }

        public Constants.ClientApps getClientApp() {
            return clientApp;
        }
    }

    public static class Response {

    }

    public static class Failure {

    }
}
