/**
 * This file is part of microWow.
 *
 * microWow is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation.
 *
 * microWow is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package wow.l10n;

import java.util.Hashtable;

/**
 * base bundle class
 *
 * @author alessandro negrin
 * @version 0.1
 */
public abstract class Bundle {

    private Hashtable messages;

    Bundle(String[][] matrix) {
        messages=new Hashtable(matrix.length);
        for (int i=0; i<matrix.length; i++) {
            messages.put(matrix[i][0], matrix[i][1]);
        }
    }
    
    public String getMessage(String key){
        return (String)messages.get(key);
    }
    
    public abstract String getAlias();
}
