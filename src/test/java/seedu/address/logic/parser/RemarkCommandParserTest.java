package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.model.person.Remark;

class RemarkCommandParserTest {
    private RemarkCommandParser parser = new RemarkCommandParser();

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE);

    @Test
    void parse_missingPersonIndex_failure() {
        assertParseFailure(parser, VALID_REMARK_AMY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    void parse_missingAllArguments_failure() {
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    void parse_invalidPersonIndex_failure() {
        // negative index
        assertParseFailure(parser, "-1" + REMARK_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + REMARK_DESC_BOB, MESSAGE_INVALID_FORMAT);
    }

    @Test
    void parse_invalidPrefix_failure() {
        // no prefix
        assertParseFailure(parser, "1 remark with no prefix", MESSAGE_INVALID_FORMAT);

        // wrong prefix
        assertParseFailure(parser, "1 rm/remark with wrong prefix", MESSAGE_INVALID_FORMAT);
    }

    @Test
    void parse_validIndexAddRemark_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + REMARK_DESC_AMY;
        RemarkCommand expectedCommand = new RemarkCommand(targetIndex, new Remark(VALID_REMARK_AMY));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    void parse_validIndexNoRemark_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + "";
        RemarkCommand expectedCommand = new RemarkCommand(targetIndex, new Remark(""));
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}