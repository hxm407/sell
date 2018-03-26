package com.xiaoming.sell.pc;

import org.springframework.boot.web.servlet.server.Jsp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoDao extends JpaRepository<Info,String> {
}
