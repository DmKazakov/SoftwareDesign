package kirilenko.cli.commands.implementation;

import kirilenko.cli.commands.AbstractCommand;
import kirilenko.cli.commands.CommandResult;
import kirilenko.cli.exceptions.CliException;
import kirilenko.cli.utils.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LsCommand extends AbstractCommand {
    /**
     * {@link AbstractCommand#AbstractCommand(List)}
     */
    public LsCommand(List<String> arguments) {
        super(arguments);
    }

    /**
     * Lists directories content.
     *
     * @param input list of directories
     * @throws CliException if there is nonexistent directory
     */
    @Override
    public CommandResult execute(List<String> input) throws CliException {
        List<String> result = new ArrayList<>();

        if (input.isEmpty()) {
            String[] dirContent = getDirectoryContent(Environment.getCurrentDirectory().toFile());
            result.add(String.join(" ", dirContent));
        }

        for (String directory : input) {
            String[] dirContent = getDirectoryContent(Environment.getFile(directory));
            result.add(String.join(" ", dirContent));
        }

        return new CommandResult(result);
    }

    private String[] getDirectoryContent(File directory) throws CliException {
        String[] dirContent = directory.list();
        if (dirContent == null) {
            throw new CliException("ls: " + directory + ": no such directory");
        }
        return dirContent;
    }
}
