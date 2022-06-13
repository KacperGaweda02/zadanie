package org.kacper.library;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@RequiredArgsConstructor
@SpringBootApplication
@EnableJpaRepositories
public class CmdApplication implements CommandLineRunner {

    private final MasterCmdView masterCmdView;

    @Override
    public void run(String... args) throws Exception {
        masterCmdView.start();
    }
}
