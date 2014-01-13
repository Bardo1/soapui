/*
 * SoapUI, copyright (C) 2004-2014 smartbear.com
 *
 * SoapUI is free software; you can redistribute it and/or modify it under the
 * terms of version 2.1 of the GNU Lesser General Public License as published by
 * the Free Software Foundation.
 *
 * SoapUI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details at gnu.org.
 */
package com.eviware.soapui.utils;

import org.fest.swing.core.Robot;
import org.fest.swing.fixture.*;

/**
 * Utility class used for generic operations on a REST project
 */
public class RestProjectUtils
{
	private static final int NEW_PROJECT_TIMEOUT = 2000;
	private static final String URI = "http://soapui.org";
	private static final String NEW_REST_PROJECT_MENU_ITEM = "New REST Project";
	private static final String NEW_REST_PROJECT_DIALOG_NAME = "New REST Project";
	private static final String OK_BUTTON_NAME = "OK";

	private final WorkspaceUtils workspaceUtils;
	private final Robot robot;

	public RestProjectUtils( Robot robot )
	{
		this.robot = robot;
		workspaceUtils = new WorkspaceUtils();
	}

	public void createNewRestProject( FrameFixture rootWindow )
	{
		openCreateNewRestProjectDialog( rootWindow );
		enterURIandClickOk();
	}

	private void openCreateNewRestProjectDialog( FrameFixture rootWindow )
	{
		JPopupMenuFixture workspace = workspaceUtils.rightClickOnWorkspace( rootWindow );
		workspace.menuItem( FestMatchers.menuItemWithText( NEW_REST_PROJECT_MENU_ITEM ) ).click();
	}

	private void enterURIandClickOk()
	{
		DialogFixture newProjectDialog = FestMatchers.dialogWithTitle( NEW_REST_PROJECT_DIALOG_NAME ).withTimeout( NEW_PROJECT_TIMEOUT )
				.using( robot );

		newProjectDialog.textBox().focus();
		newProjectDialog.textBox().click();
		newProjectDialog.textBox().setText( URI );

		JButtonFixture buttonOK = newProjectDialog.button( FestMatchers.buttonWithText( OK_BUTTON_NAME ) );
		buttonOK.click();
	}
}