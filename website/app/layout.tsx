import './globals.css'
import type {Metadata} from 'next'
import {Inter} from 'next/font/google'
import '@radix-ui/themes/styles.css';
import {Heading, Theme, ThemePanel} from '@radix-ui/themes';
import React from "react";

const inter = Inter({subsets: ['latin']})

export const metadata: Metadata = {
    title: 'Ultimate Online Judge',
    description: 'Create next Online Judge',
}

export default function RootLayout({children,}: {
    children: React.ReactNode
}) {
    return (
        <Theme appearance="light" accentColor="gray" grayColor="olive" panelBackground="solid" radius="large">
            <Heading className="text-center">Ultimate Online Judge</Heading>
            <body className={inter.className}>{children}</body>
            {/*<ThemePanel/>*/}
        </Theme>
    )
}
