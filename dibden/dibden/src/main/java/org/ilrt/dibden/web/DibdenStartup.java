/**
 * Copyright (c) 2008-2009, University of Bristol
 * Copyright (c) 2008-2009, University of Manchester
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1) Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2) Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3) Neither the names of the University of Bristol and the
 *    University of Manchester nor the names of their
 *    contributors may be used to endorse or promote products derived from this
 *    software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 */
package org.ilrt.dibden.web;

import org.ilrt.dibden.domain.Group;
import org.ilrt.dibden.domain.Role;
import org.ilrt.dibden.domain.User;
import org.ilrt.dibden.facade.UserManagementFacade;

/**
 * @author Mike Jones (mike.a.jones@bristol.ac.ukbristol.ac.uk)
 * @version $Id: DibdenStartup.java 128 2009-03-31 14:09:42Z cmmaj $
 */
public class DibdenStartup {

    public DibdenStartup(UserManagementFacade userManagementFacade) {
        this.userManagementFacade = userManagementFacade;
    }


    public void init() {


        Group userGroup = userManagementFacade.getGroup("USER_GROUP");

        if (userGroup == null) {

            userManagementFacade.createGroup("USER_GROUP", "Users group",
                    "Default Group for users");

            Role role = userManagementFacade.getRole("USER");

            if (role == null) {
                userManagementFacade.createRole("USER", "Users", "Users role");
            }

            userManagementFacade.addRoleToGroup("USER_GROUP", "USER");
        }

        Group adminGroup = userManagementFacade.getGroup("ADMIN_GROUP");

        if (adminGroup == null) {

            userManagementFacade.createGroup("ADMIN_GROUP", "Admin group",
                    "The admin user group");

            Role role = userManagementFacade.getRole("ADMIN");

            if (role == null) {
                userManagementFacade.createRole("ADMIN", "Admin", "Admin role");
            }

            userManagementFacade.addRoleToGroup("ADMIN_GROUP", "ADMIN");
            userManagementFacade.addRoleToGroup("ADMIN_GROUP", "USER");
        }

        User user = userManagementFacade.getUser("admin");

        if (user == null) {
            userManagementFacade.registerUser("admin", "admin", "Admin user", "admin@example.org");
            userManagementFacade.addUserToGroup("admin", "ADMIN_GROUP");
        }

    }

    private UserManagementFacade userManagementFacade;
}
