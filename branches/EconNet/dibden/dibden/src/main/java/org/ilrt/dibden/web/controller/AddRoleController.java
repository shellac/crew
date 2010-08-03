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
package org.ilrt.dibden.web.controller;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import org.ilrt.dibden.domain.Role;
import org.ilrt.dibden.facade.UserManagementFacade;
import org.ilrt.dibden.web.command.RoleForm;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mike Jones (mike.a.jones@bristol.ac.uk)
 * @version $Id: AddRoleController.java 128 2009-03-31 14:09:42Z cmmaj $
 */
public class AddRoleController extends SimpleFormController {

    public AddRoleController(UserManagementFacade userManagementFacade) {
        this.userManagementFacade = userManagementFacade;
    }


    public ModelAndView onSubmit(Object command, BindException errors) {

        RoleForm roleForm = (RoleForm) command;

        if (roleForm.getAddRole() != null) {
            userManagementFacade.createRole(roleForm.getRoleId(), roleForm.getName(),
                    roleForm.getDescription());
        }

        return new ModelAndView(new RedirectView("./listRoles.do"));
    }

    public void onBindAndValidate(HttpServletRequest request, Object command,
                                  BindException errors) {

        RoleForm roleForm = (RoleForm) command;

        Role role = userManagementFacade.getRole(roleForm.getRoleId());

        if (role != null) {
            errors.rejectValue("roleId", "role.roleId.exists");
        }
    }

    private UserManagementFacade userManagementFacade;
}
