/*
    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU General Public
    License as published by the Free Software Foundation; either
    version 2 of the license, or (at your option) any later version.
*/

package net.rec.contra.cjbe.editor.detail.constants;

import net.rec.contra.cjbe.editor.BrowserServices;
import org.gjt.jclasslib.structures.InvalidByteCodeException;
import org.gjt.jclasslib.structures.constants.ConstantUtf8Info;
import org.gjt.jclasslib.util.ExtendedJLabel;

import javax.swing.tree.TreePath;

/**
 * Detail pane showing a <tt>CONSTANT_Utf8</tt> constant pool entry.
 *
 * @author <a href="mailto:jclasslib@ej-technologies.com">Ingo Kegel</a>
 * @version $Revision: 1.4 $ $Date: 2006/09/25 16:00:58 $
 */
public class ConstantUtf8InfoDetailPane extends AbstractConstantInfoDetailPane {

    // Visual components

    private ExtendedJLabel lblByteLength;
    private ExtendedJLabel lblByteLengthComment;
    private ExtendedJLabel lblStringLength;
    private ExtendedJLabel lblString;

    /**
     * Constructor.
     *
     * @param services the associated editor services.
     */
    public ConstantUtf8InfoDetailPane(BrowserServices services) {
        super(services);
    }

    protected void setupLabels() {

        addDetailPaneEntry(normalLabel("Length of byte array:"),
                lblByteLength = highlightLabel(),
                lblByteLengthComment = highlightLabel());

        addDetailPaneEntry(normalLabel("Length of string:"),
                lblStringLength = highlightLabel());

        addDetailPaneEntry(normalLabel("String:"),
                null,
                lblString = highlightLabel());

    }

    public void show(TreePath treePath) {

        int constantPoolIndex = constantPoolIndex(treePath);

        try {
            ConstantUtf8Info entry = services.getClassFile().getConstantPoolUtf8Entry(constantPoolIndex);
            lblByteLength.setText(entry.getBytes().length);
            lblStringLength.setText(entry.getString().length());
            lblString.setText(getConstantPoolEntryName(constantPoolIndex));
        } catch (InvalidByteCodeException ex) {
            lblByteLength.setText(-1);
            lblStringLength.setText(-1);
            lblByteLengthComment.setText(MESSAGE_INVALID_CONSTANT_POOL_ENTRY);
        }

        super.show(treePath);
    }

}

